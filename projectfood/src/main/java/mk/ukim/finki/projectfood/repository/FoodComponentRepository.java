package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.FoodComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodComponentRepository extends JpaRepository<FoodComponent, Integer> {
}
