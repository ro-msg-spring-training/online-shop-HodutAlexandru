package ro.msg.learning.shop.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "ORDER_DETAILS")
public class OrderDetail {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    @Column
    @NotNull
    private int quantity;

    public OrderDetail() {}

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

}
