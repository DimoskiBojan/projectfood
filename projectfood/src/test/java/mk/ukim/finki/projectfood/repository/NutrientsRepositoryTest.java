package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Nutrients;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NutrientsRepositoryTest {

    @Autowired
    private NutrientsRepository nutrientsRepository;

    @Test
    public void testFindAll() {
        List<Nutrients> nutrients = nutrientsRepository.findAll();
        Assert.assertEquals(38, nutrients.size());
    }
}
