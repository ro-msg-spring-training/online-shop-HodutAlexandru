package ro.msg.learning.shop.repositories;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.stock.StockNotFoundException;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.models.entities.Location;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.models.metamodels.Location_;
import ro.msg.learning.shop.models.metamodels.Product_;
import ro.msg.learning.shop.models.metamodels.Stock_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomStockRepository {

    public List<Integer> getAllStocksIdsByLocationAndProductIds(List<ProductWithLocationAndQuantity> productsWithLocationAndQuantity) throws StockNotFoundException {
        EntityManager entityManager = Persistence.createEntityManagerFactory(ApplicationConstants.ENTITY_MANAGER).createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);

        Root<Location> locationRoot = criteriaQuery.from(Location.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Root<Stock> stockRoot = criteriaQuery.from(Stock.class);

        Path<Integer> locationId = locationRoot.get(Location_.id);
        Path<Integer> productId = productRoot.get(Product_.id);
        Path<Integer> stockId = stockRoot.get(Stock_.id);

        List<Predicate> predicates = new ArrayList();

        productsWithLocationAndQuantity.stream().forEach(product ->
                predicates.add(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(productId, product.getProduct().getId()),
                            criteriaBuilder.equal(locationId, product.getLocation().getId())
                    )
            )
        );

        criteriaQuery.select(stockId)
                .where(criteriaBuilder.or(predicates.toArray(new Predicate[0])))
                .distinct(true);

        List<Integer> stocksIds = entityManager.createQuery(criteriaQuery).getResultList();

        if(stocksIds.size() == 0) {
            throw new StockNotFoundException(ApplicationConstants.NO_STOCKS_FOUND);
        }

        return stocksIds;
    }

}
