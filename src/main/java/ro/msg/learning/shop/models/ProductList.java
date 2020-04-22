package ro.msg.learning.shop.models;

import lombok.Data;

import java.util.List;

@Data
public class ProductList {

    private List<ProductAndQuantity> products;

}
