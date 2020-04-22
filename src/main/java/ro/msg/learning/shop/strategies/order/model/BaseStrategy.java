package ro.msg.learning.shop.strategies.order.model;

import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;

import java.util.List;

public interface BaseStrategy {

    public List<ProductWithLocationAndQuantity> getResults(ProductList orderProductDetailsDto) throws LocationNotFoundException;

}
