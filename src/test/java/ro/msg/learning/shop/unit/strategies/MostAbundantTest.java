package ro.msg.learning.shop.unit.strategies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.unit.strategies.order.model.MostAbundant;
import ro.msg.learning.shop.util.TestUtils;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MostAbundantTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private MostAbundant mostAbundant;

    @Test
    public void mostAbundantStrategyTest() {
        when(this.stockRepository.findAllByProductId(1)).thenReturn(TestUtils.getDefaultStocksForProductId1());
        when(this.stockRepository.findAllByProductId(2)).thenReturn(TestUtils.getDefaultStocksForProductId2());
        when(this.stockRepository.findAllByProductId(3)).thenReturn(TestUtils.getDefaultStocksForProductId3());

        List<ProductWithLocationAndQuantity> result = this.mostAbundant.getResults(TestUtils.getProductList());

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);

        assertThat(result.get(0).getLocation().getId()).isEqualTo(3);
        assertThat(result.get(1).getLocation().getId()).isEqualTo(2);
        assertThat(result.get(2).getLocation().getId()).isEqualTo(3);
    }

}
