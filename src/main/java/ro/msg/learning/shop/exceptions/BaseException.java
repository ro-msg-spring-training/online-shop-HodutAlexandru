package ro.msg.learning.shop.exceptions;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }

}
