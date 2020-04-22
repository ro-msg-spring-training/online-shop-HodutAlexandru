package ro.msg.learning.shop.controllers.advisor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.exceptions.product.ProductNotFoundException;
import ro.msg.learning.shop.exceptions.stock.StockNotFoundException;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.payload.BaseResponse;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<BaseResponse> handleProductNotFoundException(
            ProductNotFoundException ex,
            WebRequest req
    ) {
        return new ResponseEntity(
                new BaseResponse(
                        StatusCode.NOT_FOUND,
                        ApplicationConstants.GET_PRODUCT_BY_ID_FAIL
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<BaseResponse> handleLocationNotFoundException(
            StockNotFoundException ex,
            WebRequest req
    ) {
        return new ResponseEntity(
                new BaseResponse(
                        StatusCode.NOT_FOUND,
                        ApplicationConstants.NO_LOCATION_FOUND_FOR_ORDER_PRODUCTS
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<BaseResponse> handleStockNotFoundException(
            StockNotFoundException ex,
            WebRequest req
    ) {
        return new ResponseEntity(
                new BaseResponse(
                        StatusCode.NOT_FOUND,
                        ApplicationConstants.NO_AVAILABLE_STOCK_FOUND
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleUnknownException(
            Exception ex,
            WebRequest req
    ) {
        return new ResponseEntity(
                new BaseResponse(StatusCode.INTERNAL_SERVER_ERROR,
                        ApplicationConstants.GET_PRODUCT_BY_ID_FAIL,
                        ex
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
