package ru.javaops.topjava2.repository;


import ru.javaops.topjava2.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository extends BaseRepository<Vote>{
    <T> Optional<T> findByDateAndUserId(LocalDate onDate, Integer userID, Class<T> type);
}
