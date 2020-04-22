package ro.msg.learning.shop.payload.product;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;

@Data
public class ProductResponse extends BaseResponse {

    private Product product;

    public ProductResponse(StatusCode statusCode, String message, Product product) {
        super(statusCode, message);
        this.product = product;
    }

    public ProductResponse(StatusCode statusCode, String message, Exception e, Product product) {
        super(statusCode, message, e);
        this.product = product;
    }

}
