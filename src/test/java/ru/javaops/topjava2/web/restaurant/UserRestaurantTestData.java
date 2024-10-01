package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.to.restaurant.UserViewRestaurantTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.util.List;

import static ru.javaops.topjava2.web.dish.DishTestData.*;
import static ru.javaops.topjava2.web.dish.DishTestData.DISH_TO_3;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;

public class UserRestaurantTestData {
    public static final MatcherFactory.Matcher<UserViewRestaurantTo> USER_VIEW_RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(UserViewRestaurantTo.class);

    public static final UserViewRestaurantTo USER_VIEW_RESTAURANT_TO_1 = new UserViewRestaurantTo(
            RESTAURANT_TO_1.getId(), RESTAURANT_TO_1.getName(),
            List.of(new UserViewRestaurantTo.UserViewDish(DISH_TO_1.getId(), DISH_TO_1.getName(), DISH_TO_1.getPrice()),
                    new UserViewRestaurantTo.UserViewDish(DISH_TO_2.getId(), DISH_TO_2.getName(), DISH_TO_2.getPrice()),
                    new UserViewRestaurantTo.UserViewDish(DISH_TO_3.getId(), DISH_TO_3.getName(), DISH_TO_3.getPrice())
            ),
            2
    );

    public static final UserViewRestaurantTo USER_VIEW_RESTAURANT_TO_2 = new UserViewRestaurantTo(
            RESTAURANT_TO_2.getId(), RESTAURANT_TO_2.getName(),
            List.of(new UserViewRestaurantTo.UserViewDish(DISH_TO_4.getId(), DISH_TO_4.getName(), DISH_TO_4.getPrice()),
                    new UserViewRestaurantTo.UserViewDish(DISH_TO_5.getId(), DISH_TO_5.getName(), DISH_TO_5.getPrice())
            ),
            1
    );

    public static final UserViewRestaurantTo USER_VIEW_RESTAURANT_TO_3 = new UserViewRestaurantTo(
            RESTAURANT_TO_3.getId(), RESTAURANT_TO_3.getName(),
            List.of(new UserViewRestaurantTo.UserViewDish(DISH_TO_7.getId(), DISH_TO_7.getName(), DISH_TO_7.getPrice())
            ),
            1
    );

    public static final UserViewRestaurantTo USER_VIEW_RESTAURANT_TO_4 = new UserViewRestaurantTo(
            RESTAURANT_TO_4.getId(), RESTAURANT_TO_4.getName());

    public static final List<UserViewRestaurantTo> USER_VIEW_RESTAURANT_TO_LIST = List.of(
            USER_VIEW_RESTAURANT_TO_1, USER_VIEW_RESTAURANT_TO_2, USER_VIEW_RESTAURANT_TO_3, USER_VIEW_RESTAURANT_TO_4);
}