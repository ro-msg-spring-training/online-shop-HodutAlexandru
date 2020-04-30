package ro.msg.learning.shop.unit.strategies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.unit.strategies.order.model.SingleLocation;
import ro.msg.learning.shop.util.TestUtils;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SingleLocationTest {

    @Mock
    private CustomLocationRepository customLocationRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private SingleLocation singleLocation;

    @Test
    public void singleLocationStrategyTest() {
        when(this.customLocationRepository.getAllLocations(TestUtils.getProductList())).thenReturn(TestUtils.getAllLocationsIds());
        when(this.locationRepository.findById(1)).thenReturn(Optional.of(TestUtils.getDefaultLocation()));

        List<ProductWithLocationAndQuantity> result = this.singleLocation.getResults(TestUtils.getProductList());

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);

        result.stream().forEach(product -> {
            assertThat(product.getLocation().getId()).isEqualTo(1);
            assertThat(product.getLocation().getName()).isEqualTo("Default Location");
        });

        assertThat(result.get(0).getProduct().getId()).isEqualTo(1);
        assertThat(result.get(0).getQuantity()).isEqualTo(2);

        assertThat(result.get(1).getProduct().getId()).isEqualTo(2);
        assertThat(result.get(1).getQuantity()).isEqualTo(5);

        assertThat(result.get(2).getProduct().getId()).isEqualTo(3);
        assertThat(result.get(2).getQuantity()).isEqualTo(3);


    }


}
