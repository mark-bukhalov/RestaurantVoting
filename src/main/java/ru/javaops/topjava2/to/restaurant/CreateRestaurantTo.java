package ru.javaops.topjava2.to.restaurant;

import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.NamedTo;

public class CreateRestaurantTo extends NamedTo {
    public CreateRestaurantTo(Integer id, String name) {
        super(id, name);
    }

    public static Restaurant createFromTo(CreateRestaurantTo to) {
        return new Restaurant(to.getId(), to.getName());
    }

    public static Restaurant updateFromTo(Restaurant restaurant, CreateRestaurantTo restaurantTo){
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}
