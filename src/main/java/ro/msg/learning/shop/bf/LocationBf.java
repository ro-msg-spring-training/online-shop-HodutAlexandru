package ro.msg.learning.shop.bf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.models.StockCsv;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.util.converters.StockMessageConverter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationBf {

    private final LocationRepository locationRepository;

    private final StockMessageConverter stockMessageConverter;

    public String exportStock(Integer locationId) throws IOException {
        List<StockCsv> stocks = this.locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException(
                        ApplicationConstants.NO_LOCATION_FOUND_FOR_THE_GIVEN_ID
                )).getStocks().stream().map(stock -> new StockCsv(
                        stock.getId(),
                        stock.getLocation().getName(),
                        stock.getProduct().getName(),
                        stock.getQuantity()
        )).collect(Collectors.toList());
        return this.stockMessageConverter.writeInternal(stocks, StockCsv.class);
    }

}
