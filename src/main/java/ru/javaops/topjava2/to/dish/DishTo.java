package ru.javaops.topjava2.to.dish;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.NamedTo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class DishTo extends NamedTo {

    @NotNull
    @Range(min = 1, max = 5000)
    private BigDecimal price;

    @NotNull
    private Date date;

    @NotNull
    private Integer restaurantId;

    public DishTo(Integer id, String name, BigDecimal price, Date date, Integer restaurantId) {
        super(id, name);
        this.price = price;
        this.date = date;
        this.restaurantId = restaurantId;
    }

    public static DishTo fromModel(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice(), dish.getDate(), dish.getRestaurant().getId());
    }

    public static List<DishTo> fromListModel(List<Dish> dishList) {
        return dishList.stream()
                .map(DishTo::fromModel)
                .toList();
    }
}
