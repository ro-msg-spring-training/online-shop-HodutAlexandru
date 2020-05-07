package ro.msg.learning.shop.models.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "customer_id"
    )
    private Customer customer;

    @Column
    @NotNull
    private LocalDateTime createdAt;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "order",
            orphanRemoval = true
    )
    private List<Location> shippedFrom;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "location_id"
    )
    private Location deliveryAddress;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "order",
            orphanRemoval = true
    )
    private List<OrderDetail> orderDetails;

}
