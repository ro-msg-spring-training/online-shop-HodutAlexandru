package ro.msg.learning.shop.models;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Product;

@Data
public class ProductAndQuantity {
    private Product product;
    private int quantity;
}
