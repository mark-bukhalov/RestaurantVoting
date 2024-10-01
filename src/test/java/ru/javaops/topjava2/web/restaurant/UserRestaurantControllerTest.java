package ru.javaops.topjava2.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.restaurant.UserRestaurantController.REST_URL;
import static ru.javaops.topjava2.web.restaurant.UserRestaurantTestData.USER_VIEW_RESTAURANT_TO_LIST;
import static ru.javaops.topjava2.web.restaurant.UserRestaurantTestData.USER_VIEW_RESTAURANT_TO_MATCHER;
import static ru.javaops.topjava2.web.user.UserTestData.USER_MAIL;

class UserRestaurantControllerTest extends AbstractControllerTest {
    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_VIEW_RESTAURANT_TO_MATCHER.contentJson(USER_VIEW_RESTAURANT_TO_LIST));
    }
}