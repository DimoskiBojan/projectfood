package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Component;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComponentRepository extends JpaRepository<Component, Integer> {

    Component findByName(String name);
}
