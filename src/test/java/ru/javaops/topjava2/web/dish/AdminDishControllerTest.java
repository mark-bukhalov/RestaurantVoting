package ru.javaops.topjava2.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.service.DishService;
import ru.javaops.topjava2.to.dish.DishTo;

import ru.javaops.topjava2.util.JsonUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;
import ru.javaops.topjava2.web.restaurant.AdminRestaurantController;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.dish.AdminDishController.REST_URL;
import static ru.javaops.topjava2.web.dish.DishTestData.*;
import static ru.javaops.topjava2.web.user.UserTestData.ADMIN_MAIL;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;

class AdminDishControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    DishRepository dishRepository;

    @Autowired
    DishService dishService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TO_MATCHER.contentJson(DISH_TO_LIST));
    }

    @Test
    void getAllUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(AdminRestaurantController.REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DISH_TO_ID_1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_TO_MATCHER.contentJson(DISH_TO_1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DISH_TO_ID_1))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotExist() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DISH_TO_ID_NOT_EXIST))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + DISH_TO_ID_1))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertFalse(dishRepository.findById(DISH_TO_ID_1).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        DishTo newDish = DishTestData.getNew();
        ResultActions resultActions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isCreated());

        DishTo createdDish = DISH_TO_MATCHER.readFromJson(resultActions);
        DishTo expectedDish = new DishTo(9, newDish.getName(), newDish.getPrice(), newDish.getDate(), newDish.getRestaurantId());

        DISH_TO_MATCHER.assertMatch(createdDish, expectedDish);
        DISH_TO_MATCHER.assertMatch(dishService.get(9), expectedDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createUnprocessable() throws Exception {
        DishTo newDish = DishTestData.getNew();
        newDish.setPrice(BigDecimal.valueOf(0));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        DishTo updateDish = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + DISH_TO_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updateDish)))
                .andExpect(status().isNoContent());

        DishTo expectedDish = new DishTo(updateDish.getId(), updateDish.getName(), updateDish.getPrice(), updateDish.getDate(), updateDish.getRestaurantId());

        DISH_TO_MATCHER.assertMatch(dishService.get(updateDish.getId()), expectedDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateUnprocessable() throws Exception {
        DishTo updateDish = DishTestData.getUpdated();
        updateDish.setName("q");
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + DISH_TO_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updateDish)))
                .andExpect(status().isUnprocessableEntity());
    }
}