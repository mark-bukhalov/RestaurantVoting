package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.to.dish.DishTo;
import ru.javaops.topjava2.util.validation.ValidationUtil;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Cacheable("dish_all")
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Cacheable("dish_id")
    public Dish get(Integer id) {
        return dishRepository.getExisted(id);
    }

    @Caching(evict = {
            @CacheEvict(value = "dish_all", allEntries = true),
            @CacheEvict(value = "dish_id")
    })
    public void delete(Integer id) {
        dishRepository.deleteExisted(id);
    }

    @Caching(
            put = {@CachePut(value = "dish_id", key = "#result.id")},
            evict = {@CacheEvict(value = "dish_all", allEntries = true)})
    public Dish create(DishTo dishTo) {
        Dish dish = modelFromTo(dishTo);
        ValidationUtil.checkNew(dishTo);
        dishRepository.save(dish);
        return dish;
    }

    @Caching(
            evict = {@CacheEvict(value = "dish_all", allEntries = true),
                    @CacheEvict(value = "dish_id", key = "#id")})
    public void update(DishTo dishTo, Integer id) {
        ValidationUtil.assureIdConsistent(dishTo, id);
        Dish dish = dishRepository.getExisted(id);
        dishRepository.save(updateFromTo(dish, dishTo));
    }

    private Dish modelFromTo(DishTo dishTo) {
        return new Dish(dishTo.getId(),
                dishTo.getName(),
                dishTo.getPrice(),
                dishTo.getDate(),
                restaurantRepository.getReferenceById(dishTo.getRestaurantId()));
    }

    private Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        dish.setDate(dishTo.getDate());
        if (!Objects.equals(dish.getRestaurant().getId(), dishTo.getRestaurantId())) {
            dish.setRestaurant(restaurantRepository.getReferenceById(dishTo.getRestaurantId()));
        }
        return dish;
    }
}
