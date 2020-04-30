package ro.msg.learning.shop.unit.serialization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;
import ro.msg.learning.shop.models.dto.StockCsvDto;
import ro.msg.learning.shop.util.TestUtils;
import ro.msg.learning.shop.util.converters.CsvConverter;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SerializationTest {

    @InjectMocks
    private CsvConverter<StockCsvDto> csvConverter;

    @Test
    public void serializeDataTest() throws IOException {
        String serializedData = this.csvConverter.toCsv(StockCsvDto.class, TestUtils.getStockCsvList());

        assertThat(serializedData).isNotNull();
        assertThat(serializedData).isNotEmpty();
        assertThat(serializedData).isEqualTo(TestUtils.getCsvStocks());
    }

    @Test
    public void deserializeDataTest() throws IOException {
        this.csvConverter.setUploadedFile(ResourceUtils.getFile("classpath:test/static/Stocks.csv"));
        List<StockCsvDto> stocks = this.csvConverter.fromCsv(StockCsvDto.class);

        assertThat(stocks).isNotNull();
        assertThat(stocks.size()).isGreaterThan(0);
        assertThat(stocks).hasSize(3);
        assertThat(stocks).isEqualTo(TestUtils.getStockCsvList());
    }

}
