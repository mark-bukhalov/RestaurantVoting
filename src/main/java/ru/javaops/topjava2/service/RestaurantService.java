package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.restaurant.CreateRestaurantTo;
import ru.javaops.topjava2.to.restaurant.RestaurantTo;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;

    public List<RestaurantTo> getAll() {
        return RestaurantTo.fromListModel(repository.findAll());
    }

    public RestaurantTo get(Integer id) {
        return RestaurantTo.fromModel(repository.getExisted(id));
    }

    public void delete(Integer id) {
        repository.deleteExisted(id);
    }

    public RestaurantTo create(CreateRestaurantTo createRestaurantTo) {
        Restaurant restaurant = CreateRestaurantTo.createFromTo(createRestaurantTo);
        ValidationUtil.checkNew(restaurant);
        Restaurant created = repository.save(restaurant);
        return RestaurantTo.fromModel(created);
    }

    @Transactional
    public void update(CreateRestaurantTo createRestaurantTo, Integer id) {
        ValidationUtil.assureIdConsistent(createRestaurantTo, id);
        Restaurant restaurant = repository.getExisted(id);
        repository.save(CreateRestaurantTo.updateFromTo(restaurant, createRestaurantTo));
    }
}
