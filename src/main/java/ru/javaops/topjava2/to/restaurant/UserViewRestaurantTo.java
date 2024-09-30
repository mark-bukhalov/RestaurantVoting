package ru.javaops.topjava2.to.restaurant;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserViewRestaurantTo {
    Integer id;
    String name;
    Integer voteCount;
    List<UserViewDish> menu;

    public UserViewRestaurantTo(Integer id, String name, List<UserViewDish> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public UserViewRestaurantTo(Integer id, String name) {
        this(id, name, new ArrayList<>());
    }

    @Value
    public static class UserViewDish {
        Integer id;
        String name;
        BigDecimal price;
    }
}