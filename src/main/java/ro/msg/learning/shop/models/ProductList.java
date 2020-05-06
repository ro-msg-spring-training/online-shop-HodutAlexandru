package ro.msg.learning.shop.models;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Location;

import java.util.List;

@Data
public class ProductList {

    private Location deliveryAddress;
    private List<ProductAndQuantity> products;

}
