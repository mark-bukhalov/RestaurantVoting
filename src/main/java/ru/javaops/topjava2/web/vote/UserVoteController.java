package ru.javaops.topjava2.web.vote;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.service.VoteService;
import ru.javaops.topjava2.to.vote.VoteTo;
import ru.javaops.topjava2.web.AuthUser;

@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {
    public static final String REST_URL = "/api/user/vote";

    private final VoteService service;

    @GetMapping
    public VoteTo getUserVote(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get vote for user {}", authUser.getUser().getId());
        return service.getUserVoteCurrentDay(authUser.getUser().getId());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public VoteTo createOrUpdate(@AuthenticationPrincipal AuthUser authUser,
                                 @RequestBody @Valid VoteTo voteTo) {
        log.info("set vote restaurantId {} for user {}", voteTo.getRestaurantId(), authUser.getUser().getId());
        return service.createOrUpdate(authUser.getUser().getId(), voteTo.getRestaurantId());
    }
}
