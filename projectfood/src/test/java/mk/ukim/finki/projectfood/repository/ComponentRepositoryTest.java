package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Component;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentRepositoryTest {

    @Autowired
    private ComponentRepository componentRepository;

    @Test
    public void testFindAll() {
        List<Component> components = componentRepository.findAll();
        Assert.assertEquals(1673, components.size());
    }
}
