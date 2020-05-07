package ro.msg.learning.shop.models.metamodels;

import ro.msg.learning.shop.models.entities.Location;
import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.entities.Stock;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Stock.class)
public class Stock_ {

    public static volatile SingularAttribute<Stock, Integer> id;

    public static volatile SingularAttribute<Stock, Product> product;

    public static volatile SingularAttribute<Stock, Location> location;

    public static volatile SingularAttribute<Stock, Integer> quantity;

}
