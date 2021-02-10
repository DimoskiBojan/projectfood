package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.views.FoodsShowView;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface FoodService {
    List<Food> getAllFoods();

    List<FoodsShowView> getAllFoodsShow();

    List<Food> getFoodsByCategory(String category);

    Page<Food> getAllFoods(int page, int size);

    Food getFood(Integer id);

    FoodsShowView getFoodShow(Integer id);

    Food getFoodByName(String name);

    Food updateFoodSameAs(Integer id, String sameAs);

    Food updateFoodSameAsSingleUrl(Integer id, String sameAs);

    Food updateFoodFooDBId(Integer id, Integer foodbId);

    List<Food> mapFoodToFoods();

    void mapFoodToFOODON();

    void mapFoodToSNOMEDCT();

    String lookupExternal(String term);

    Map<Integer, Integer> countPossibleMappings();

    void refreshMV();
}
