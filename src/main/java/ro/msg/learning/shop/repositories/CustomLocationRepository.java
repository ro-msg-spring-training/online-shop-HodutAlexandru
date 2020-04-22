package ro.msg.learning.shop.repositories;

import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.metamodels.Stock_;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomLocationRepository {

    public List<Integer> getAllLocations(ProductList orderDetailsDto) throws LocationNotFoundException {
        EntityManager entityManager = Persistence.createEntityManagerFactory(ApplicationConstants.ENTITY_MANAGER).createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
        Root<Stock> root = criteriaQuery.from(Stock.class);

        Path<Integer> productId = root.get(Stock_.product).get("id");
        Path<Integer> quantity = root.get(Stock_.quantity);
        Path<Integer> locationId = root.get(Stock_.location).get("id");

        List<Predicate> predicates = new ArrayList();

        orderDetailsDto.getProducts().stream().forEach(product ->
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(productId, product.getProduct().getId()),
                                criteriaBuilder.greaterThanOrEqualTo(quantity, product.getQuantity())
                        )
                ));

        criteriaQuery.select(locationId)
                .where(criteriaBuilder.or(predicates.toArray(new Predicate[0])))
                .groupBy(locationId)
                .having(criteriaBuilder.equal(criteriaBuilder.count(productId), orderDetailsDto.getProducts().size()));

        List<Integer> locationsIds = entityManager.createQuery(criteriaQuery).getResultList();

        if(locationsIds.size() == 0) {
            throw new LocationNotFoundException(ApplicationConstants.NO_LOCATION_FOUND_FOR_ORDER_PRODUCTS);
        }

        return locationsIds;
    }

}
