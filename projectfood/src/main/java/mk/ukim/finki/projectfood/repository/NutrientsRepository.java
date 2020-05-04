package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutrientsRepository extends JpaRepository<Nutrients, Integer> {
}
