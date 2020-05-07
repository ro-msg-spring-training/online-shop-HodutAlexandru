package ro.msg.learning.shop.payload.product;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;

import java.util.List;

@Data
public class ProductsResponse extends BaseResponse {

    private List<Product> products;

    public ProductsResponse(StatusCode statusCode, String message, List<Product> products) {
        super(statusCode, message);
        this.products = products;
    }

    public ProductsResponse(StatusCode statusCode, String message, Exception e, List<Product> products) {
        super(statusCode, message, e);
        this.products = products;
    }

}
