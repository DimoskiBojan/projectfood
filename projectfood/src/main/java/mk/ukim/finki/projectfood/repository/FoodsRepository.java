package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Foods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodsRepository extends JpaRepository<Foods, Integer> {
    Optional<Foods> findByName(String name);
}
