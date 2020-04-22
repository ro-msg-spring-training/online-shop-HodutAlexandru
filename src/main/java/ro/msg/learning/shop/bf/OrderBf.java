package ro.msg.learning.shop.bf;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.constants.ApplicationConstants;
import ro.msg.learning.shop.exceptions.location.LocationNotFoundException;
import ro.msg.learning.shop.mappers.product.OrderMapper;
import ro.msg.learning.shop.models.ProductAndQuantity;
import ro.msg.learning.shop.models.entities.Order;
import ro.msg.learning.shop.models.entities.OrderDetail;
import ro.msg.learning.shop.models.entities.Stock;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.dto.OrderDto;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.models.enums.StatusCode;
import ro.msg.learning.shop.models.enums.StrategyType;
import ro.msg.learning.shop.payload.order.OrderResponse;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.strategies.order.OrderStrategy;
import ro.msg.learning.shop.strategies.order.model.BaseStrategy;
import ro.msg.learning.shop.strategies.order.model.MostAbundant;
import ro.msg.learning.shop.strategies.order.model.SingleLocation;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderBf {

    @Value("${shop.strategy}")
    private String shopStrategy;

    private final OrderRepository orderRepository;

    private final StockRepository stockRepository;

    public OrderResponse createOrder(OrderDto orderDto) {
        Order newOrder = OrderMapper.INSTANCE.orderDtoToOrder(orderDto);

        ProductList productList = new ProductList();
        productList.setProducts(orderDto.getProductsWithQuantity());

        this.strategy(productList).stream().forEach(orderDetail -> {
            newOrder.getOrderDetails().add(
                    new OrderDetail(
                        newOrder,
                        orderDetail.getProduct(),
                        orderDetail.getQuantity()
                    )
            );
        });

        Order savedOrder = this.orderRepository.save(newOrder);

        return new OrderResponse(
                StatusCode.CREATED,
                ApplicationConstants.ORDER_CREATE_SUCCESS,
                savedOrder
        );
    }

    public List<ProductWithLocationAndQuantity> strategy(ProductList productList) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(OrderStrategy.class);

        BaseStrategy strategy = shopStrategy == StrategyType.SINGLE_LOCATION.getValue()
                ? applicationContext.getBean(SingleLocation.class)
                : applicationContext.getBean(MostAbundant.class);

        return this.updateStocks(strategy.getResults(productList));
    }

    private List<ProductWithLocationAndQuantity> updateStocks(List<ProductWithLocationAndQuantity> productsWithLocationAndQuantity) {
        this.stockRepository.findAllByLocationIdsAndProductIds(productsWithLocationAndQuantity).stream().forEach(stock -> {
            productsWithLocationAndQuantity.stream().forEach(productWithLocationAndQuantity -> {
                if(
                        stock.getProduct().getId() == productWithLocationAndQuantity.getProduct().getId()
                        && stock.getLocation().getId() == productWithLocationAndQuantity.getLocation().getId()
                ) {
                    stock.setQuantity(stock.getQuantity() - productWithLocationAndQuantity.getQuantity());
                    this.stockRepository.save(stock);
                }
            });
        });

        return productsWithLocationAndQuantity;
    }

}
