package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Foods;

import java.util.List;

public interface FoodsService {
    List<Foods> getAllFoods();

    Foods getFood(Integer id);

    Foods getFoodByName(String name);

    void populateSameAs();

}
