package mk.ukim.finki.projectfood.repository;

import mk.ukim.finki.projectfood.model.Contents;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentsRepositoryTest {

    @Autowired
    private ContentsRepository contentsRepository;

    @Test
    public void testFindAll() {
        List<Contents> contents = contentsRepository.findAll();
        Assert.assertEquals(1682148, contents.size());
    }

    @Test
    public void testFindBySourceIdAndSourceType() {
        List<Contents> contents = contentsRepository.findBySourceIdAndSourceType(13, "Compound");
        Assert.assertEquals(1, contents.size());
    }
}
