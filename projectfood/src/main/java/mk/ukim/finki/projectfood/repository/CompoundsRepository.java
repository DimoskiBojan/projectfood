package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Compounds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompoundsRepository extends JpaRepository<Compounds, Integer> {

    Compounds findByNameIgnoreCase(String name);
}
