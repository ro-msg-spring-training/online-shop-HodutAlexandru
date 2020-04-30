package ro.msg.learning.shop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ResourceUtils;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.repositories.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class IntegrationTestConfig {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Bean
    CommandLineRunner runner(ProductRepository productRepository) throws IOException {
        return args -> {
            // Clean up db data and prepare for testing
            this.stockRepository.deleteAll();
            this.productRepository.deleteAll();
            this.locationRepository.deleteAll();
            this.supplierRepository.deleteAll();

            ObjectMapper mapper = new ObjectMapper();

            List<Stock> stocks = Arrays.asList(mapper.readValue(ResourceUtils.getFile("classpath:test/integration/stock/stocks.json"), Stock[].class));
            this.stockRepository.saveAll(stocks);
        };
    }

}
