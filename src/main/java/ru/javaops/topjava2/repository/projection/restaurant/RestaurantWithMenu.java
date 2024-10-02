package ru.javaops.topjava2.repository.projection.restaurant;
import java.math.BigDecimal;

public interface RestaurantWithMenu {
    Integer getId();
    String getName();
    Dish getDish();

    interface Dish{
        Integer getId();
        String getName();
        BigDecimal getPrice();
    }
}