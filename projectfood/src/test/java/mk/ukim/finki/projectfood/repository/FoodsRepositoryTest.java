package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Foods;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodsRepositoryTest {

    @Autowired
    private FoodsRepository foodsRepository;

    @Test
    public void testFindAll() {
        List<Foods> foods = foodsRepository.findAll();
        Assert.assertEquals(907, foods.size());
    }

}
