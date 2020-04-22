package ro.msg.learning.shop.models.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "LOCATIONS")
public class Location {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String country;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private String street;

    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Stock> stocks;

    @ManyToMany(
            mappedBy = "locations",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @Fetch(value = FetchMode.SELECT)
    private Set<Order> orders;

    @OneToMany(
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Revenue> revenues;

}
