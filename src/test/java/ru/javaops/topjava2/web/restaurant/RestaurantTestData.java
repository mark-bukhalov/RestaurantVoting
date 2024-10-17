package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.to.restaurant.RestaurantTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);
    public static final int RESTAURANT_TO_1_ID = 1;
    public static final int RESTAURANT_TO_2_ID = 2;
    public static final int RESTAURANT_TO_3_ID = 3;
    public static final int RESTAURANT_TO_4_ID = 4;
    public static final int RESTAURANT_TO_NOT_EXIST_ID = 100;

    public static final RestaurantTo RESTAURANT_TO_1 = new RestaurantTo(RESTAURANT_TO_1_ID, "Gusteaus");
    public static final RestaurantTo RESTAURANT_TO_2 = new RestaurantTo(RESTAURANT_TO_2_ID, "Los Pollos Hermanos");
    public static final RestaurantTo RESTAURANT_TO_3 = new RestaurantTo(RESTAURANT_TO_3_ID, "Double R Diner");
    public static final RestaurantTo RESTAURANT_TO_4 = new RestaurantTo(RESTAURANT_TO_4_ID, "Burger King");

    public static final List<RestaurantTo> RESTAURANT_TO_LIST = List.of(RESTAURANT_TO_1, RESTAURANT_TO_2, RESTAURANT_TO_3, RESTAURANT_TO_4);

    public static RestaurantTo getNew() {
        return new RestaurantTo(null, "New restaurant");
    }

    public static RestaurantTo getUpdated() {
        return new RestaurantTo(RESTAURANT_TO_1_ID, "Updated restaurant");
    }
}
