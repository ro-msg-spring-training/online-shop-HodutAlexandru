package ro.msg.learning.shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.models.entities.Product;

@Data
@AllArgsConstructor
public class ProductAndQuantity {
    private Product product;
    private int quantity;
}
