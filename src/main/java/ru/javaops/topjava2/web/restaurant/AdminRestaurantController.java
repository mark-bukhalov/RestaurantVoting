package ru.javaops.topjava2.web.restaurant;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.service.RestaurantService;
import ru.javaops.topjava2.to.restaurant.RestaurantTo;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {
    public static final String REST_URL = "/api/admin/restaurant";

    private final RestaurantService service;

    private final Converter<List<Restaurant>, List<RestaurantTo>> restaurantListToConverter;
    private final Converter<Restaurant, RestaurantTo> restaurantToConverter;

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return restaurantListToConverter.convert(service.getAll());
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable Integer id) {
        log.info("get {}", id);
        return restaurantToConverter.convert(service.get(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> createWithLocation(@Valid @RequestBody RestaurantTo createRestaurantTo) {
        log.info("create {}", createRestaurantTo);
        RestaurantTo restaurantTo = restaurantToConverter.convert(service.create(createRestaurantTo));
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantTo.getId())
                .toUri();
        return ResponseEntity.created(uri).body(restaurantTo);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable Integer id) {
        log.info("update {} with id {}", restaurantTo, id);
        service.update(restaurantTo, id);
    }
}
