package ro.msg.learning.shop.models.dto;

import lombok.Data;
import ro.msg.learning.shop.models.ProductAndQuantity;

import java.util.List;

@Data
public class OrderDto {

    private String timestamp;
    private String address;
    private List<ProductAndQuantity> productsWithQuantity;

}
