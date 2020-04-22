package ro.msg.learning.shop.models.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_location",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    @Fetch(value = FetchMode.SELECT)
    private Set<Location> locations;

    @OneToMany(
            fetch = FetchType.LAZY
    )
    private List<OrderDetail> orderDetails;

}
