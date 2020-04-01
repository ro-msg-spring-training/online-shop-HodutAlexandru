package ro.msg.learning.shop.models;

import lombok.Data;

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
