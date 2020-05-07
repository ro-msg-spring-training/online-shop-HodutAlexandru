package ro.msg.learning.shop.exceptions.stock;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.msg.learning.shop.exceptions.BaseException;

@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends BaseException {

    public StockNotFoundException(String message) {
        super(message);
    }

}
