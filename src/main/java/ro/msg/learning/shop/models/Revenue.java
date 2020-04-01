package ro.msg.learning.shop.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "REVENUES")
public class Revenue {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull
    private LocalDate date;

    @Column
    @NotNull
    private BigDecimal sum;

}
