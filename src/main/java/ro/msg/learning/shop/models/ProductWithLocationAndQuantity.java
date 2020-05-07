package ro.msg.learning.shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.models.entities.Location;
import ro.msg.learning.shop.models.entities.Product;

@Data
@AllArgsConstructor
public class ProductWithLocationAndQuantity {

    private Location location;
    private Product product;
    private int quantity;

}
