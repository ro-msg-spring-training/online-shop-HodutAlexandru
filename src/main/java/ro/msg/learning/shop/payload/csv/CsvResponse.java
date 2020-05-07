package ro.msg.learning.shop.payload.csv;

import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;

public class CsvResponse extends BaseResponse {

    public CsvResponse(StatusCode statusCode, String message) {
        super(statusCode, message);
    }

}
