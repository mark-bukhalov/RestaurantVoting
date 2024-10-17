package ru.javaops.topjava2.to.converter;

import org.springframework.core.convert.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.to.dish.DishTo;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DishListToConverter implements Converter<List<Dish>, List<DishTo>> {

    private final Converter<Dish, DishTo> dishToConverter;

    @Override
    public List<DishTo> convert(List<Dish> dishList) {
        return dishList.stream()
                .map(dishToConverter::convert)
                .toList();
    }
}

