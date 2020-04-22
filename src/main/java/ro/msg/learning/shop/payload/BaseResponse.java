package ro.msg.learning.shop.payload;

import lombok.Data;
import ro.msg.learning.shop.models.enums.StatusCode;

@Data
public class BaseResponse {

    private StatusCode statusCode;
    private String message;
    private StackTraceElement[] stackTrace;

    public BaseResponse(StatusCode statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public BaseResponse(StatusCode statusCode, String message, Exception e) {
        this.statusCode = statusCode;
        this.message = message;
        this.stackTrace = e.getStackTrace();
    }

}
