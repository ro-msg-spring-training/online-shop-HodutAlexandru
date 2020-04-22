package ro.msg.learning.shop.models.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "PRODUCT_CATEGORIES")
public class ProductCategory {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Product> products;

}
