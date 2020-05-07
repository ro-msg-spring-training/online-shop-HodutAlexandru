package ro.msg.learning.shop.exceptions.customer;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.msg.learning.shop.exceptions.BaseException;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends BaseException {

    public CustomerNotFoundException(String message) {
        super(message);
    }

}
