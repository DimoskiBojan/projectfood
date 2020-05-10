package mk.ukim.finki.projectfood.repository.views;

import mk.ukim.finki.projectfood.model.views.FoodsShowView;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface FoodsShowViewRepository extends JpaRepository<FoodsShowView, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW foods_show", nativeQuery = true)
    void refreshMV();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"foodComponents"})
    @Query("select f from FoodsShowView f")
    List<FoodsShowView> fetchAll();


}
