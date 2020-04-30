package ro.msg.learning.shop.unit.strategies.order;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repositories.CustomLocationRepository;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.unit.strategies.order.model.MostAbundant;
import ro.msg.learning.shop.unit.strategies.order.model.SingleLocation;

@Configuration
@RequiredArgsConstructor
public class OrderStrategy {

    private final CustomLocationRepository customLocationRepository;

    private final LocationRepository locationRepository;

    private final StockRepository stockRepository;

    @Bean(name = "singleLocation")
    public SingleLocation singleLocation() {
        return new SingleLocation(customLocationRepository, locationRepository);
    }

    @Bean(name = "mostAbundant")
    public MostAbundant mostAbundant() {
        return new MostAbundant(stockRepository);
    }

}
