package ru.javaops.topjava2.web.restaurant;

import ru.javaops.topjava2.to.restaurant.CreateRestaurantTo;
import ru.javaops.topjava2.to.restaurant.RestaurantTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class, "registered", "password");
    public static final int RESTAURANT_TO_1_ID = 1;
    public static final int RESTAURANT_TO_2_ID = 2;
    public static final int RESTAURANT_TO_3_ID = 3;

    public static final RestaurantTo RESTAURANT_TO_1 = new RestaurantTo(RESTAURANT_TO_1_ID, "Gusteaus");
    public static final RestaurantTo RESTAURANT_TO_2 = new RestaurantTo(RESTAURANT_TO_2_ID, "Los Pollos Hermanos");
    public static final RestaurantTo RESTAURANT_TO_3 = new RestaurantTo(RESTAURANT_TO_3_ID, "Double R Diner");

    public static final List<RestaurantTo> RestaurantToList = List.of(RESTAURANT_TO_1, RESTAURANT_TO_2, RESTAURANT_TO_3);


    public static CreateRestaurantTo getNew() {
        return new CreateRestaurantTo(null, "New restaurant");
    }

    public static CreateRestaurantTo getUpdated() {
        return new CreateRestaurantTo(RESTAURANT_TO_1_ID, "Updated restaurant");
    }
}
