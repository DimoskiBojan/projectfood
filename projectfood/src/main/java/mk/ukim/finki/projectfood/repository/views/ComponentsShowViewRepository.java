package mk.ukim.finki.projectfood.repository.views;

import mk.ukim.finki.projectfood.model.views.ComponentsShowView;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComponentsShowViewRepository extends JpaRepository<ComponentsShowView, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW components_show", nativeQuery = true)
    void refreshMV();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"foodComponents"})
    @Query("select c from ComponentsShowView c")
    List<ComponentsShowView> fetchAll();
}
