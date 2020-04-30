package ro.msg.learning.shop.util;

import ro.msg.learning.shop.models.ProductAndQuantity;
import ro.msg.learning.shop.models.ProductList;
import ro.msg.learning.shop.models.ProductWithLocationAndQuantity;
import ro.msg.learning.shop.models.dto.OrderDto;
import ro.msg.learning.shop.models.dto.StockCsvDto;
import ro.msg.learning.shop.models.entities.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestUtils {

    public static Location getLocationForSerialization() {
        Location location = new Location();
        location.setId(1);
        location.setName("Location1");

        location.setStocks(getStocks());

        return location;
    }

    public static List<Stock> getStocks() {
        Location location = new Location();
        location.setId(1);
        location.setName("Location1");

        Product product = new Product();
        product.setName("Product1");

        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setQuantity(20);
        stock1.setProduct(product);
        stock1.setLocation(location);

        Stock stock2 = new Stock();
        stock2.setId(2);
        stock2.setProduct(product);
        stock2.setLocation(location);
        stock2.setQuantity(30);

        Stock stock3 = new Stock();
        stock3.setId(3);
        stock3.setProduct(product);
        stock3.setLocation(location);
        stock3.setQuantity(25);

        return Arrays.asList(stock1, stock2, stock3);
    }

    public static List<StockCsvDto> getStockCsvList() {
        StockCsvDto stock1 = new StockCsvDto();
        stock1.setStockId(1);
        stock1.setLocation("Location1");
        stock1.setProduct("Product1");
        stock1.setQuantity(20);

        StockCsvDto stock2 = new StockCsvDto();
        stock2.setStockId(2);
        stock2.setLocation("Location1");
        stock2.setProduct("Product1");
        stock2.setQuantity(30);

        StockCsvDto stock3 = new StockCsvDto();
        stock3.setStockId(3);
        stock3.setLocation("Location1");
        stock3.setProduct("Product1");
        stock3.setQuantity(25);

        return Arrays.asList(stock1, stock2, stock3);
    }

    public static String getCsvStocks() {
        return "location\tproduct\tquantity\tstockId\n" +
                "Location1\tProduct1\t20\t1\n" +
                "Location1\tProduct1\t30\t2\n" +
                "Location1\tProduct1\t25\t3\n";
    }

    public static Stock getDefaultStock() {
        Location location = new Location();
        location.setId(1);

        Product product = new Product();
        product.setId(1);

        Stock stock = new Stock();

        stock.setId(1);
        stock.setLocation(location);
        stock.setProduct(product);
        stock.setQuantity(5);

        return stock;
    }

    public static List<Stock> getDefaultStocksForProductId1() {
        Product product = new Product();
        product.setId(1);

        Location location1 = new Location();
        location1.setId(1);

        Location location2 = new Location();
        location2.setId(2);

        Location location3 = new Location();
        location3.setId(3);

        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        Stock stock3 = new Stock();

        stock1.setId(1);
        stock1.setProduct(product);
        stock1.setLocation(location1);
        stock1.setQuantity(8);

        stock2.setId(2);
        stock2.setProduct(product);
        stock2.setLocation(location2);
        stock2.setQuantity(15);

        stock3.setId(3);
        stock3.setProduct(product);
        stock3.setLocation(location3);
        stock3.setQuantity(20);

        return Arrays.asList(stock1, stock2, stock3);
    }

    public static List<Stock> getDefaultStocksForProductId2() {
        Product product = new Product();
        product.setId(2);

        Location location1 = new Location();
        location1.setId(1);

        Location location2 = new Location();
        location2.setId(2);

        Location location3 = new Location();
        location3.setId(3);

        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        Stock stock3 = new Stock();

        stock1.setId(1);
        stock1.setProduct(product);
        stock1.setLocation(location1);
        stock1.setQuantity(1);

        stock2.setId(2);
        stock2.setProduct(product);
        stock2.setLocation(location2);
        stock2.setQuantity(10);

        stock3.setId(3);
        stock3.setProduct(product);
        stock3.setLocation(location3);
        stock3.setQuantity(8);

        return Arrays.asList(stock1, stock2, stock3);
    }

    public static List<Stock> getDefaultStocksForProductId3() {
        Product product = new Product();
        product.setId(3);

        Location location1 = new Location();
        location1.setId(1);

        Location location2 = new Location();
        location2.setId(2);

        Location location3 = new Location();
        location3.setId(3);

        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        Stock stock3 = new Stock();

        stock1.setId(1);
        stock1.setProduct(product);
        stock1.setLocation(location1);
        stock1.setQuantity(7);

        stock2.setId(2);
        stock2.setProduct(product);
        stock2.setLocation(location2);
        stock2.setQuantity(3);

        stock3.setId(3);
        stock3.setProduct(product);
        stock3.setLocation(location3);
        stock3.setQuantity(10);

        return Arrays.asList(stock1, stock2, stock3);
    }

    public static Location getDefaultLocation() {
        Location location = new Location();

        location.setId(1);
        location.setName("Default Location");

        return location;
    }

    public static OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setLocations(getDefaultLocations());
        orderDto.setProductsWithQuantity(getProductList().getProducts());

        return orderDto;
    }

    public static Order getNewlyCreatedOrder() {
        Order newOrder = new Order();

        Product product1 = new Product();
        product1.setId(1);

        Customer customer = new Customer();
        customer.setId(1);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setId(1);
        orderDetail1.setProduct(product1);
        orderDetail1.setQuantity(5);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setId(2);
        orderDetail2.setProduct(product1);
        orderDetail2.setQuantity(7);

        OrderDetail orderDetail3 = new OrderDetail();
        orderDetail3.setId(3);
        orderDetail3.setProduct(product1);
        orderDetail3.setQuantity(8);

        List<OrderDetail> orderDetails = Arrays.asList(orderDetail1, orderDetail2, orderDetail3);

        newOrder.setId(1);
        newOrder.setCustomer(customer);
        newOrder.setLocations(getDefaultLocations());
        newOrder.setOrderDetails(orderDetails);

        return newOrder;
    }

    public static Set<Location> getDefaultLocations() {
        Location location1 = new Location();
        location1.setId(1);

        Location location2 = new Location();
        location1.setId(2);

        Location location3 = new Location();
        location1.setId(3);

        Set<Location> locations = new HashSet();
        locations.addAll(Arrays.asList(location1, location2, location3));

        return locations;
    }

    public static ProductList getProductList() {
        ProductList productList = new ProductList();

        Product product1 = new Product();
        product1.setId(1);
        Product product2 = new Product();
        product2.setId(2);
        Product product3 = new Product();
        product3.setId(3);

        ProductAndQuantity productAndQuantity1 = new ProductAndQuantity();
        productAndQuantity1.setProduct(product1);
        productAndQuantity1.setQuantity(2);

        ProductAndQuantity productAndQuantity2 = new ProductAndQuantity();
        productAndQuantity2.setProduct(product2);
        productAndQuantity2.setQuantity(5);

        ProductAndQuantity productAndQuantity3 = new ProductAndQuantity();
        productAndQuantity3.setProduct(product3);
        productAndQuantity3.setQuantity(3);

        productList.setProducts(Arrays.asList(productAndQuantity1, productAndQuantity2, productAndQuantity3));

        return productList;
    }

    public static List<ProductWithLocationAndQuantity> getProductsWithLocationAndQuantity() {
        Product product1 = new Product();
        product1.setId(1);
        Product product2 = new Product();
        product2.setId(2);
        Product product3 = new Product();
        product3.setId(3);

        Location location1 = new Location();
        location1.setId(1);
        Location location2 = new Location();
        location2.setId(2);
        Location location3 = new Location();
        location3.setId(3);

        ProductWithLocationAndQuantity productWithLocationAndQuantity1 = new ProductWithLocationAndQuantity(location1, product1, 5);
        ProductWithLocationAndQuantity productWithLocationAndQuantity2 = new ProductWithLocationAndQuantity(location2, product2, 7);
        ProductWithLocationAndQuantity productWithLocationAndQuantity3 = new ProductWithLocationAndQuantity(location3, product3, 8);

        return Arrays.asList(productWithLocationAndQuantity1, productWithLocationAndQuantity2, productWithLocationAndQuantity3);
    }

    public static List<Integer> getAllLocationsIds() {
        return Arrays.asList(1, 2, 3);
    }

    public static List<Integer> getStocksIds() {
        return Arrays.asList(1, 2, 3);
    }

}
