package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    public List<Stock> findAllByProductId(Integer productId);

    public List<Stock> findAllByLocationIdsAndProductIds(List<ProductWithLocationAndQuantity> productsWithLocationAndQuantity);

}
