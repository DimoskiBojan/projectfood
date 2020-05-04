package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.views.FoodsShowView;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FoodService {
    List<Food> getAllFoods();

    List<FoodsShowView> getAllFoodsShow();

    Page<Food> getAllFoods(int page, int size);

    Food getFood(Integer id);

    FoodsShowView getFoodShow(Integer id);

    Food getFoodByName(String name);

    Food updateFoodSameAs(Integer id, String sameAs);

    void mapFoodToFoods();

    void mapFoodToFOODON();

    void refreshMV();
}
