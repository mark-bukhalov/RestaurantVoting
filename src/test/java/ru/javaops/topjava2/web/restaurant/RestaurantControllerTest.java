package ru.javaops.topjava2.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.service.RestaurantService;
import ru.javaops.topjava2.to.restaurant.CreateRestaurantTo;
import ru.javaops.topjava2.to.restaurant.RestaurantTo;
import ru.javaops.topjava2.util.JsonUtil;
import ru.javaops.topjava2.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.restaurant.RestaurantController.REST_URL;
import static ru.javaops.topjava2.web.user.UserTestData.*;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.*;

class RestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    RestaurantRepository repository;

    @Autowired
    RestaurantService service;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RestaurantToList));
    }

    @Test
    void getAllUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT_TO_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RESTAURANT_TO_1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByUser() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT_TO_1_ID))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotExist() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT_TO_NOT_EXIST_ID))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RESTAURANT_TO_1_ID))
                .andExpect(status().isNoContent())
                .andDo(print());
        assertFalse(repository.findById(RESTAURANT_TO_1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        CreateRestaurantTo createRestaurantTo = RestaurantTestData.getNew();
        ResultActions resultActions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createRestaurantTo)))
                .andExpect(status().isCreated());

        RestaurantTo created = RESTAURANT_TO_MATCHER.readFromJson(resultActions);
        RestaurantTo expected = new RestaurantTo(4, createRestaurantTo.getName());

        RESTAURANT_TO_MATCHER.assertMatch(created, expected);
        RESTAURANT_TO_MATCHER.assertMatch(service.get(created.getId()), expected);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createUnprocessable() throws Exception {
        CreateRestaurantTo createRestaurantTo = RestaurantTestData.getNew();
        createRestaurantTo.setName("q");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createRestaurantTo)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        CreateRestaurantTo updateRestaurantTo = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_TO_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updateRestaurantTo)))
                .andExpect(status().isNoContent());

        RestaurantTo expected = new RestaurantTo(updateRestaurantTo.getId(), updateRestaurantTo.getName());

        RESTAURANT_TO_MATCHER.assertMatch(service.get(updateRestaurantTo.getId()), expected);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateUnprocessable() throws Exception {
        CreateRestaurantTo updateRestaurantTo = RestaurantTestData.getUpdated();
        updateRestaurantTo.setName("q");
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + RESTAURANT_TO_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updateRestaurantTo)))
                .andExpect(status().isUnprocessableEntity());
    }

}