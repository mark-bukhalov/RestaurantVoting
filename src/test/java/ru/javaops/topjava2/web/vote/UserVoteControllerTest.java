package ru.javaops.topjava2.web.vote;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.topjava2.to.vote.VoteTo;
import ru.javaops.topjava2.util.CurrentDateTime;
import ru.javaops.topjava2.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.RESTAURANT_TO_1_ID;
import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.RESTAURANT_TO_3_ID;
import static ru.javaops.topjava2.web.user.UserTestData.*;
import static ru.javaops.topjava2.web.vote.UserVoteController.REST_URL;
import static ru.javaops.topjava2.web.vote.VoteTestData.USER_VOTE_TO;
import static ru.javaops.topjava2.web.vote.VoteTestData.VOTE_TO_MATCHER;

class UserVoteControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getUserVote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(USER_VOTE_TO));
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void getGuestVoteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void CreateGuestVote() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT_TO_1_ID)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(new VoteTo(RESTAURANT_TO_1_ID)));
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void UpdateAdminVoteBeforeCheckTime() throws Exception {

        LocalTime lc = LocalTime.of(11, 0, 0);

        try (MockedStatic<CurrentDateTime> localTimeMockedStatic = Mockito.mockStatic(CurrentDateTime.class)) {
            localTimeMockedStatic.when(CurrentDateTime::getCurrentTime).thenReturn(lc);

            perform(MockMvcRequestBuilders.post(REST_URL)
                    .param("restaurantId", String.valueOf(RESTAURANT_TO_3_ID)))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(VOTE_TO_MATCHER.contentJson(new VoteTo(RESTAURANT_TO_3_ID)));
        }
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void UpdateAdminVoteAfterCheckTime() throws Exception {

        LocalTime lc = LocalTime.of(13, 0, 0);

        try (MockedStatic<CurrentDateTime> localTimeMockedStatic = Mockito.mockStatic(CurrentDateTime.class)) {
            localTimeMockedStatic.when(CurrentDateTime::getCurrentTime).thenReturn(lc);

            perform(MockMvcRequestBuilders.post(REST_URL)
                    .param("restaurantId", String.valueOf(RESTAURANT_TO_3_ID)))
                    .andExpect(status().isConflict())
                    .andDo(print());
        }
    }
}