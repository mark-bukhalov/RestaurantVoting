package ru.javaops.topjava2.to.dish;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import ru.javaops.topjava2.to.NamedTo;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class DishTo extends NamedTo {

    @NotNull
    @Range(min = 1, max = 5000)
    private BigDecimal price;

    @NotNull
    private LocalDate date;

    @NotNull
    private Integer restaurantId;

    public DishTo(Integer id, String name, BigDecimal price, LocalDate date, Integer restaurantId) {
        super(id, name);
        this.price = price;
        this.date = date;
        this.restaurantId = restaurantId;
    }
}
