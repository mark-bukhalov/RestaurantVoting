package ru.javaops.topjava2.web.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.service.RestaurantService;
import ru.javaops.topjava2.to.restaurant.UserViewRestaurantTo;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController {
    public static final String REST_URL = "/api/user/restaurant";

    private final RestaurantService service;

    @GetMapping
    public List<UserViewRestaurantTo> getAll() {
       return service.getAllUserView();
    }
}