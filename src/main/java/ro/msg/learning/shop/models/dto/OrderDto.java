package ro.msg.learning.shop.models.dto;

import lombok.Data;
import ro.msg.learning.shop.models.ProductAndQuantity;
import ro.msg.learning.shop.models.entities.Location;

import java.util.List;

@Data
public class OrderDto {

    private String timestamp;
    private Location deliveryAddress;
    private List<ProductAndQuantity> productsWithQuantity;

}
