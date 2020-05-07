package ro.msg.learning.shop.exceptions.product;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.msg.learning.shop.exceptions.BaseException;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends BaseException {

    public ProductNotFoundException(String message, String searchCriteria, String value) {
        super(message + " " + searchCriteria + ": " + value);
    }

}
