package ro.msg.learning.shop.mappers.stock;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.models.dto.StockCsvDto;
import ro.msg.learning.shop.models.entities.Stock;

@Mapper
public interface StockCsvMapper {

    StockCsvMapper INSTANCE = Mappers.getMapper(StockCsvMapper.class);

    @Mappings({
            @Mapping(target = "stockId", source = "stock.id"),
            @Mapping(target = "location", source = "stock.location.name"),
            @Mapping(target = "product", source = "stock.product.name"),
            @Mapping(target = "quantity", source = "quantity")
    })
    StockCsvDto stockToStockCsvDto(Stock stock);

}
