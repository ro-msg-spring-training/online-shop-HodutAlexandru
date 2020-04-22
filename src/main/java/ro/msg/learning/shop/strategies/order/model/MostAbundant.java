package ro.msg.learning.shop.strategies.order.model;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.BaseException;
import ro.msg.learning.shop.exceptions.stock.StockNotFoundException;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.util.Util;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MostAbundant implements BaseStrategy{

    private final StockRepository stockRepository;

    public List<ProductWithLocationAndQuantity> getResults(ProductList productList) throws BaseException {
        List<ProductWithLocationAndQuantity> result = new ArrayList();

        productList.getProducts().stream().forEach(requestedProduct -> {
            Stock chosenStock = this.stockRepository.findAllByProductId(requestedProduct.getProduct().getId()).stream()
                    .filter(availableStock -> availableStock.getQuantity() >= requestedProduct.getQuantity())
                    .max(Util::compareStocks)
                    .orElseThrow(() -> new StockNotFoundException(ApplicationConstants.NO_AVAILABLE_STOCK_FOUND));

            result.add(new ProductWithLocationAndQuantity(
                    chosenStock.getLocation(),
                    requestedProduct.getProduct(),
                    requestedProduct.getQuantity()
            ));
        });

        return result;
    }
}
