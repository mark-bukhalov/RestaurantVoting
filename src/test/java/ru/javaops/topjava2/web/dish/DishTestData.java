package ru.javaops.topjava2.web.dish;

import ru.javaops.topjava2.to.dish.DishTo;
import ru.javaops.topjava2.web.MatcherFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(DishTo.class);
    public static final int DISH_TO_ID_1 = 1;
    public static final int DISH_TO_ID_2 = 2;
    public static final int DISH_TO_ID_3 = 3;
    public static final int DISH_TO_ID_4 = 4;
    public static final int DISH_TO_ID_5 = 5;
    public static final int DISH_TO_ID_6 = 6;
    public static final int DISH_TO_ID_7 = 7;
    public static final int DISH_TO_ID_8 = 8;
    public static final int DISH_TO_ID_NOT_EXIST = 100;

    public static final LocalDate CURRENT_DATE = LocalDate.now();
    public static final LocalDate YESTERDAY_DATE = LocalDate.now().minusDays(1);
    public static final LocalDate TOMORROW_DATE = LocalDate.now().plusDays(1);

    public static final DishTo DISH_TO_1 = new DishTo(DISH_TO_ID_1, "Ratatouille", BigDecimal.valueOf(100.00), CURRENT_DATE, 1);
    public static final DishTo DISH_TO_2 = new DishTo(DISH_TO_ID_2, "Spaghetti", BigDecimal.valueOf(120.5), CURRENT_DATE, 1);
    public static final DishTo DISH_TO_3 = new DishTo(DISH_TO_ID_3, "Pizza", BigDecimal.valueOf(55.55), CURRENT_DATE, 1);
    public static final DishTo DISH_TO_4 = new DishTo(DISH_TO_ID_4, "Burger", BigDecimal.valueOf(50), CURRENT_DATE, 2);
    public static final DishTo DISH_TO_5 = new DishTo(DISH_TO_ID_5, "Fries", BigDecimal.valueOf(30), CURRENT_DATE, 2);
    public static final DishTo DISH_TO_6 = new DishTo(DISH_TO_ID_6, "Fries", BigDecimal.valueOf(20), YESTERDAY_DATE, 2);
    public static final DishTo DISH_TO_7 = new DishTo(DISH_TO_ID_7, "Cherry pie", BigDecimal.valueOf(45.1), CURRENT_DATE, 3);
    public static final DishTo DISH_TO_8 = new DishTo(DISH_TO_ID_8, "Fried eggs", BigDecimal.valueOf(45.1), TOMORROW_DATE, 3);

    public static final List<DishTo> DISH_TO_LIST = List.of(DISH_TO_1, DISH_TO_2, DISH_TO_3, DISH_TO_4, DISH_TO_5, DISH_TO_6, DISH_TO_7, DISH_TO_8);

    public static DishTo getNew() {
        return new DishTo(null, "New food", BigDecimal.valueOf(33.3), CURRENT_DATE, 3);
    }

    public static DishTo getUpdated() {
        return new DishTo(DISH_TO_ID_1, "New Ratatouille", BigDecimal.valueOf(200.00), CURRENT_DATE, 2);
    }
}