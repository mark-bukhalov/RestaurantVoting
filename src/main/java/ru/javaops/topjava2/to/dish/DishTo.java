package ru.javaops.topjava2.to.dish;

import lombok.Getter;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.NamedTo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
public class DishTo extends NamedTo {

    private final BigDecimal price;
    private final Date date;
    private final Integer restaurantId;

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
