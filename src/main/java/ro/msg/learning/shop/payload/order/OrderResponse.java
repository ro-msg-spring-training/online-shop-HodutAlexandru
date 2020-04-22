package ro.msg.learning.shop.payload.order;

import ro.msg.learning.shop.models.entities.Order;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;

public class OrderResponse  extends BaseResponse {

    private Order order;

    public OrderResponse(StatusCode statusCode, String message, Order order) {
        super(statusCode, message);
        this.order = order;
    }

    public OrderResponse(StatusCode statusCode, String message, Exception e, Order order) {
        super(statusCode, message, e);
        this.order = order;
    }

}
