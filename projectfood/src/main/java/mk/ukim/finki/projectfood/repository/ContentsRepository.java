package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents, Integer> {
    List<Contents> findBySourceIdAndSourceType(Integer sourceId, String sourceType);
}
