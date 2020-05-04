package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Compounds;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompoundsRepositoryTest {

    @Autowired
    private CompoundsRepository compoundsRepository;

    @Test
    public void testFindAll() {
        List<Compounds> compounds = compoundsRepository.findAll();
        Assert.assertEquals(28771, compounds.size());
    }
}
