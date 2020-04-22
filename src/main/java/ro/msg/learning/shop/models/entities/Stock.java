package ro.msg.learning.shop.models.entities;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Location;
import ro.msg.learning.shop.models.entities.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "STOCKS")
public class Stock {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Location location;

    @Column
    @NotNull
    private int quantity;

}
