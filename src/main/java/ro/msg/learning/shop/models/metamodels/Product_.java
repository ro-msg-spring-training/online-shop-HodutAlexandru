package ro.msg.learning.shop.models.metamodels;

import ro.msg.learning.shop.models.entities.Product;
import ro.msg.learning.shop.models.entities.Stock;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_ {

    public static volatile SingularAttribute<Stock, Integer> id;

}
