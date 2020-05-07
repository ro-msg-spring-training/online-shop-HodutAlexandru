package ro.msg.learning.shop.exceptions.location;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.msg.learning.shop.exceptions.BaseException;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends BaseException {

    public LocationNotFoundException(String message) {
        super(message);
    }

}
