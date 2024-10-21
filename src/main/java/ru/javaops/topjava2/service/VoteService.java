package ru.javaops.topjava2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.error.NotFoundException;
import ru.javaops.topjava2.model.Vote;
import ru.javaops.topjava2.repository.RestaurantRepository;
import ru.javaops.topjava2.repository.UserRepository;
import ru.javaops.topjava2.repository.VoteRepository;
import ru.javaops.topjava2.repository.projection.vote.VoteRestaurantId;
import ru.javaops.topjava2.to.vote.VoteTo;
import ru.javaops.topjava2.util.CurrentDateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public VoteTo getUserVoteCurrentDay(Integer userId) {
        VoteRestaurantId voteRestaurantId = voteRepository.findByDateAndUserId(LocalDate.now(), userId, VoteRestaurantId.class)
                .orElseThrow(() -> new NotFoundException("Not found"));

        return new VoteTo(voteRestaurantId.getRestaurantId());
    }

    @Transactional
    public VoteTo createOrUpdate(Integer userId, Integer restaurantId) {
        LocalDate currentDate = LocalDate.now();
        Vote vote = voteRepository.findByDateAndUserId(currentDate, userId, Vote.class).orElseGet(Vote::new);
        if (vote.isNew()) {
            vote.setDate(currentDate);
            vote.setUser(userRepository.getReferenceByIdExisted(userId));
            vote.setRestaurant(restaurantRepository.getReferenceByIdExisted(restaurantId));
            voteRepository.save(vote);
        } else {
            if (!Objects.equals(vote.getRestaurant().getId(), restaurantId)) {
                checkTimeChange();
                vote.setRestaurant(restaurantRepository.getReferenceByIdExisted(restaurantId));
                voteRepository.save(vote);
            }
        }
        return new VoteTo(vote.getRestaurant().getId());
    }

    private void checkTimeChange() {
        if (LocalTime.of(11, 0).isBefore(CurrentDateTime.getCurrentTime())) {
            throw new DataIntegrityViolationException("The user can change his vote only until 11:00");
        }
    }
}