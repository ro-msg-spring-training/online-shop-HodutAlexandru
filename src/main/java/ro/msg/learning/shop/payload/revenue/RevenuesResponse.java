package ro.msg.learning.shop.payload.revenue;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Revenue;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;

import java.util.List;

@Data
public class RevenuesResponse extends BaseResponse {

    List<Revenue> revenues;

    public RevenuesResponse(StatusCode statusCode, String message, List<Revenue> revenues) {
        super(statusCode, message);
        this.revenues = revenues;
    }

}
