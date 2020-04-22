package ro.msg.learning.shop.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.dto.ProductDto;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(
            source = "pid", target = "id"
    )
    Product productDtoToProduct(ProductDto productDto);

}
