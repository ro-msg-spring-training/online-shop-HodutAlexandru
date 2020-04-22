package ro.msg.learning.shop.models.entities;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "SUPPLIERS")
public class Supplier {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products;

}
