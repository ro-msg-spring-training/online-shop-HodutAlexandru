package ro.msg.learning.shop.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "ORDER_DETAILS")
public class OrderDetail {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Order order;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products;

    @Column
    @NotNull
    private int quantity;

}
