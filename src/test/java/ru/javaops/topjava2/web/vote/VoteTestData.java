package ru.javaops.topjava2.web.vote;

import ru.javaops.topjava2.to.vote.VoteTo;
import ru.javaops.topjava2.web.MatcherFactory;

import static ru.javaops.topjava2.web.restaurant.RestaurantTestData.RESTAURANT_TO_1_ID;

public class VoteTestData {
    public static final MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class);
    public static final VoteTo USER_VOTE_TO = new VoteTo(RESTAURANT_TO_1_ID);
    public static final VoteTo ADMIN_VOTE_TO = new VoteTo(RESTAURANT_TO_1_ID);
}
