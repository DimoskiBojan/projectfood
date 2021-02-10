package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Food;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class FoodRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void whenFindByName_thenReturnFood() {
        Food food = new Food();
        food.setId(1);
        food.setName("apple");
        food.setCategory("Fruit and vegetables");
        entityManager.persistAndFlush(food);

        Food found = foodRepository.findByName(food.getName());
        assertThat(found.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Food fromDb = foodRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenNullName_thenReturnNull() {
        Food fromDb = foodRepository.findByName(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindById_thenReturnFood() {
        Food food = new Food();
        food.setId(1);
        food.setName("apple");
        food.setCategory("Fruit and vegetables");
        entityManager.persistAndFlush(food);

        Food fromDb = foodRepository.findById(food.getId()).orElse(null);
        assertThat(fromDb.getName()).isEqualTo(food.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Food fromDb = foodRepository.findById(-11).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfFoods_whenFindAll_thenReturnAllFoods() {
        Food apple = new Food();
        apple.setId(1);
        apple.setName("apple");
        Food lemon = new Food();
        lemon.setId(2);
        lemon.setName("lemon");
        Food orange = new Food();
        orange.setId(3);
        orange.setName("orange");

        entityManager.persist(apple);
        entityManager.persist(lemon);
        entityManager.persist(orange);
        entityManager.flush();

        List<Food> allEmployees = foodRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(Food::getName).containsOnly(apple.getName(), lemon.getName(), orange.getName());
    }
}
