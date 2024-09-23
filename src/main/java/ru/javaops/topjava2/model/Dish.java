package ru.javaops.topjava2.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity {

    public Dish(Integer id, String name, BigDecimal price, Date date, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.date = date;
        this.restaurant = restaurant;
    }

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;
}
