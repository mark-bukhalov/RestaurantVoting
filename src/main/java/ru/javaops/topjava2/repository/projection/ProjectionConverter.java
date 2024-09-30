package ru.javaops.topjava2.repository.projection;

import lombok.experimental.UtilityClass;
import ru.javaops.topjava2.to.restaurant.UserViewRestaurantTo;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class ProjectionConverter {
    public static UserViewRestaurantTo toUserViewRestaurantTo(RestaurantWithMenu projection) {
        if (projection.getDish() == null) {
            return new UserViewRestaurantTo(projection.getId(),
                    projection.getName(),
                    null);
        } else {
            return new UserViewRestaurantTo(projection.getId(),
                    projection.getName(),
                    new ArrayList<>(
                            Collections.singleton(new UserViewRestaurantTo.UserViewDish(projection.getDish().getId(),
                                    projection.getDish().getName(),
                                    projection.getDish().getPrice()))
                    ));
        }

    }

    public static List<UserViewRestaurantTo> toUserViewRestaurantTo(List<RestaurantWithMenu> projection) {
        Map<Integer, UserViewRestaurantTo> idRestMap = new HashMap<>();

        for (RestaurantWithMenu restRecord : projection) {
            if (idRestMap.containsKey(restRecord.getId())) {
                UserViewRestaurantTo userViewRestaurantTo = idRestMap.get(restRecord.getId());
                RestaurantWithMenu.Dish dish = restRecord.getDish();
                if (dish != null) {
                    userViewRestaurantTo.getMenu().add(new UserViewRestaurantTo.UserViewDish(dish.getId(), dish.getName(), dish.getPrice()));
                }
            } else {
                UserViewRestaurantTo userViewRestaurantTo = new UserViewRestaurantTo(restRecord.getId(), restRecord.getName());
                RestaurantWithMenu.Dish dish = restRecord.getDish();
                if (dish != null) {
                    userViewRestaurantTo.getMenu().add(new UserViewRestaurantTo.UserViewDish(dish.getId(), dish.getName(), dish.getPrice()));
                }
                idRestMap.put(restRecord.getId(), userViewRestaurantTo);
            }
        }
        return idRestMap.values().stream().toList();
    }

    public static Map<Integer, Integer> toVoteCountMap(List<RestaurantIdVoteCount> voteCounts) {
        return voteCounts.stream()
                .collect(
                        Collectors.toMap(
                                vc -> (vc.getId()),
                                vc -> (vc.getCount().intValue()))
                );
    }

    public static List<UserViewRestaurantTo> mergeRestaurantWithMenuAndVote(List<RestaurantWithMenu> restaurantWithMenu, List<RestaurantIdVoteCount> voteCount) {
        List<UserViewRestaurantTo> userViewRestaurantTo = ProjectionConverter.toUserViewRestaurantTo(restaurantWithMenu);
        Map<Integer, Integer> voteCountMap = ProjectionConverter.toVoteCountMap(voteCount);

        for (UserViewRestaurantTo to : userViewRestaurantTo) {
            Integer count = voteCountMap.getOrDefault(to.getId(), 0);
            to.setVoteCount(count);
        }

        return userViewRestaurantTo;
    }
}