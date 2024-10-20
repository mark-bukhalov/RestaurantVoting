package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.projection.ProjectionConverter;
import ru.javaops.topjava2.to.restaurant.RestaurantTo;
import ru.javaops.topjava2.to.restaurant.UserViewRestaurantTo;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;

    @Cacheable("restaurant_all")
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Cacheable("restaurant_id")
    public Restaurant get(Integer id) {
        return repository.getExisted(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "restaurant_all", allEntries = true),
            @CacheEvict(value = "restaurant_id")
    })
    public void delete(Integer id) {
        repository.deleteExisted(id);
    }

    @Caching(
            put = {@CachePut(value = "restaurant_id", key = "#result.id")},
            evict = {@CacheEvict(value = "restaurant_all", allEntries = true)})
    public Restaurant create(RestaurantTo createRestaurantTo) {
        Restaurant restaurant = createFromTo(createRestaurantTo);
        ValidationUtil.checkNew(restaurant);
        return repository.save(restaurant);
    }

    @Caching(
            evict = {@CacheEvict(value = "restaurant_all", allEntries = true),
                    @CacheEvict(value = "restaurant_id", key = "#id")})
    @Transactional
    public void update(RestaurantTo createRestaurantTo, Integer id) {
        ValidationUtil.assureIdConsistent(createRestaurantTo, id);
        Restaurant restaurant = repository.getExisted(id);
        repository.save(updateFromTo(restaurant, createRestaurantTo));
    }

    @Transactional
    public List<UserViewRestaurantTo> getAllUserView() {
        return ProjectionConverter.mergeRestaurantWithMenuAndVote(
                repository.findAllWithMenuOnDate(LocalDate.now()),
                repository.countRestaurantVoteOnDate(LocalDate.now()));
    }

    public static Restaurant createFromTo(RestaurantTo to) {
        return new Restaurant(to.getId(), to.getName());
    }

    public static Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}
