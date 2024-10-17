package ru.javaops.topjava2.to.restaurant;

import lombok.ToString;
import ru.javaops.topjava2.to.NamedTo;

@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {
    public RestaurantTo(Integer id, String name) {
        super(id, name);
    }
}
