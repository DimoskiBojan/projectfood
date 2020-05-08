package mk.ukim.finki.projectfood.repository;


import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.Foods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

   /* @Transactional
    @Query(value = "UPDATE food\n" +
            "SET foodb_id = foods.id\n" +
            "FROM\n" +
            "foods\n" +
            "WHERE\n" +
            "food.name = foods.name;", nativeQuery = true)
    void mapFoodToFoods();*/

    Food findByName(String name);

    List<Food> findByCategory(String category);

}
