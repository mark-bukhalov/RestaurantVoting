package ru.javaops.topjava2.to.restaurant;

import lombok.ToString;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.to.NamedTo;

import java.util.List;

@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {
    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }

    public static RestaurantTo fromModel(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> fromListModel(List<Restaurant> restaurantList) {
        return restaurantList.stream()
                .map(RestaurantTo::fromModel)
                .toList();
    }
}
