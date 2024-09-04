package ru.javaops.topjava2.web.restaurant;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.topjava2.service.RestaurantService;
import ru.javaops.topjava2.to.restaurant.CreateRestaurantTo;
import ru.javaops.topjava2.to.restaurant.RestaurantTo;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    public static final String REST_URL = "api/restaurant";

    private final RestaurantService service;

    @GetMapping
    public List<RestaurantTo> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable Integer id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> createWithLocation(@Valid @RequestBody CreateRestaurantTo createRestaurantTo) {
        RestaurantTo restaurantTo = service.create(createRestaurantTo);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantTo.getId())
                .toUri();
        return ResponseEntity.created(uri).body(restaurantTo);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)

    public void update(@Valid @RequestBody CreateRestaurantTo restaurantTo, @PathVariable Integer id) {
        service.update(restaurantTo, id);
    }
}
