package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Foods;

import java.util.List;

public interface FoodsService {
    List<Foods> getAllFoods();

    Foods getFoodByName(String name);

    void populateSameAs();

}
