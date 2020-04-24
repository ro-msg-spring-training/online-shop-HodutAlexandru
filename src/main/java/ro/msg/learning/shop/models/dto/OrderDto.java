package ro.msg.learning.shop.models.dto;

import lombok.Data;
import ro.msg.learning.shop.models.ProductAndQuantity;
import ro.msg.learning.shop.models.entities.Location;

import java.util.List;
import java.util.Set;

@Data
public class OrderDto {

    private String timestamp;
    private Set<Location> locations;
    private List<ProductAndQuantity> productsWithQuantity;

}
