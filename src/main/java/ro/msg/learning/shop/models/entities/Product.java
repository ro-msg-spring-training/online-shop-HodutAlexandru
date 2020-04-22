package ro.msg.learning.shop.models.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private BigDecimal price;

    @Column
    @NotNull
    private double weight;

    @ManyToOne
    private ProductCategory category;

    @ManyToOne
    private Supplier supplier;

    @OneToMany(
            fetch = FetchType.LAZY
    )
    private List<Stock> stoks;

    @Column
    private String imageUrl;

}
