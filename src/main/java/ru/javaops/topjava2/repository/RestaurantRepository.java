package ru.javaops.topjava2.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.repository.projection.restaurant.RestaurantIdVoteCount;
import ru.javaops.topjava2.repository.projection.restaurant.RestaurantWithMenu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {
    @Cacheable("restaurant_menu")
    @Query("""
                SELECT r.id as id, r.name as name, m as dish
                  FROM Restaurant AS r
                   LEFT JOIN r.menu as m
                   on m.date = :onDate
            """)
    List<RestaurantWithMenu> findAllWithMenuOnDate(@Param("onDate") LocalDate onDate);

    @Query("""
            SELECT r.id AS id,
                   COUNT(v) AS count
              FROM Restaurant r
         LEFT JOIN Vote v ON r.id = v.restaurant.id
                         AND v.date = :onDate
          GROUP BY r.id
         """)
    List<RestaurantIdVoteCount> countRestaurantVoteOnDate(@Param("onDate") LocalDate onDate);


}
