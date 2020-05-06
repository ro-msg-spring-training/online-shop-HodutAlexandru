package ro.msg.learning.shop.unit.strategies.order.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.models.MapQuestOptions;
import ro.msg.learning.shop.models.ProductAndQuantity;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.models.entities.Location;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.payload.mapquest.MapQuestRequest;
import ro.msg.learning.shop.payload.mapquest.MapQuestResponse;
import ro.msg.learning.shop.repositories.CustomLocationRepository;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.util.Util;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Greedy implements BaseStrategy {

    private final RestTemplate restTemplate;

    private final CustomLocationRepository customLocationRepository;

    private final LocationRepository locationRepository;

    @Override
    public List<ProductWithLocationAndQuantity> getResults(ProductList productList) throws LocationNotFoundException {
        List<ProductWithLocationAndQuantity> productsWithLocationAndQuantities = new ArrayList();

        List<Location> allLocations = this.customLocationRepository.getAllLocations(productList).stream().map(id ->
            this.locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(ApplicationConstants.NO_LOCATION_FOUND_FOR_THE_GIVEN_ID))
        ).collect(Collectors.toList());

        List<String> locationsCities = new ArrayList();
        locationsCities.add(productList.getDeliveryAddress().getCity());
        locationsCities.addAll(allLocations.stream().map(location -> location.getCity()).collect(Collectors.toList()));

        MapQuestOptions options = new MapQuestOptions(true, false);
        MapQuestRequest req = new MapQuestRequest(locationsCities, options);

        HttpEntity<MapQuestRequest> request = new HttpEntity<>(req);
        MapQuestResponse response = this.restTemplate.postForObject(Util.MAP_QUEST_URL, request, MapQuestResponse.class);

        Map<Double, String> unorderedLocationsWithDistances = new HashMap();

        for(int i = 0; i<locationsCities.size(); i++) {
            unorderedLocationsWithDistances.put(response.getDistance().get(i), locationsCities.get(i));
        }

        Map<Double, String> orderedLocationsWithDistances = new TreeMap<>(unorderedLocationsWithDistances);

        List<String> locationNames = new ArrayList(orderedLocationsWithDistances.values()).subList(1, orderedLocationsWithDistances.values().size());

        List<Location> allLocationsOrdered = locationNames.stream().map(lName -> this.locationRepository.findByCity(lName)).collect(Collectors.toList());

        for(Location location : allLocationsOrdered) {
            for(ProductAndQuantity product : productList.getProducts()) {
                location.getStocks().stream().forEach(stock -> {
                    if(stock.getProduct().getId() == product.getProduct().getId()) {
                        ProductWithLocationAndQuantity newProduct = new ProductWithLocationAndQuantity(location, stock.getProduct(), product.getQuantity());
                        List<Integer> addedProductIds = productsWithLocationAndQuantities.stream().map(productWithLocationAndQuantity -> productWithLocationAndQuantity.getProduct().getId()).collect(Collectors.toList());
                        if(!addedProductIds.contains(product.getProduct().getId())){
                            productsWithLocationAndQuantities.add(newProduct);
                        }
                    }
                });
            }

            if(productsWithLocationAndQuantities.size() == productList.getProducts().size()) {
                break;
            }
        }

        return productsWithLocationAndQuantities;
    }

}
