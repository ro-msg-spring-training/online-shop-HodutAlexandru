package ro.msg.learning.shop.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.models.entities.Order;
import ro.msg.learning.shop.models.dto.OrderDto;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(
        source = "oid", target = "id"
    )
    Order orderDtoToOrder(OrderDto orderDto);

}
