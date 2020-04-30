package ro.msg.learning.shop.models.entities;

import lombok.Data;
import ro.msg.learning.shop.models.entities.Location;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "REVENUES")
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    private LocalDate date;

    @Column
    @NotNull
    private BigDecimal sum;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "location_id"
    )
    private Location location;

}
