package ru.javaops.topjava2.to.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.dish.DishTo;

@Component
public class DishToConverter implements Converter<Dish, DishTo> {
    @Override
    public DishTo convert(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice(), dish.getDate(), dish.getRestaurant().getId());
    }
}
