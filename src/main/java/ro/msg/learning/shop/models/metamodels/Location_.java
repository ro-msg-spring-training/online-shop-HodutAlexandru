package ro.msg.learning.shop.models.metamodels;

import ro.msg.learning.shop.models.entities.Location;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Location.class)
public class Location_ {

    public static volatile SingularAttribute<Location, Integer> id;

}
