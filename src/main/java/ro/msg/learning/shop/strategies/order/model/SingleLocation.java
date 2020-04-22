package ro.msg.learning.shop.strategies.order.model;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.models.entities.Location;
import ro.msg.learning.shop.repositories.CustomLocationRepository;
import ro.msg.learning.shop.repositories.LocationRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SingleLocation implements BaseStrategy{

    private final CustomLocationRepository customLocationRepository;

    private final LocationRepository locationRepository;

    public List<ProductWithLocationAndQuantity> getResults(ProductList productList) throws LocationNotFoundException {
        List<Integer> allLocations = this.customLocationRepository.getAllLocations(productList);
        Location selectedLocation = this.locationRepository.findById(allLocations.get(0)).orElseThrow(
                () -> new LocationNotFoundException(ApplicationConstants.NO_LOCATION_FOUND_FOR_ORDER_PRODUCTS)
        );

        return productList.getProducts().stream().map(product -> new ProductWithLocationAndQuantity(
                    selectedLocation,
                    product.getProduct(),
                    product.getQuantity()
            )).collect(Collectors.toList()
        );
    }

}
