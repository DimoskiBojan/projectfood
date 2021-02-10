package mk.ukim.finki.projectfood.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import mk.ukim.finki.projectfood.ProjectfoodApplication;
import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.repository.FoodRepository;
import mk.ukim.finki.projectfood.service.FoodService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProjectfoodApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@Sql({"/createFoodsMV.sql"})
public class FoodApiIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FoodRepository repository;

    @Autowired
    private FoodService service;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    @WithMockUser("admin")
    public void whenValidInput_thenUpdateFoodSameAs() throws IOException, Exception {
        createTestFood(1, "apple");
        String sameAs = "http://dbpedia.org/resource/Apple";

        mvc.perform(patch("/api/food/1/sameas").param("sameAs", sameAs))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sameAs", is(sameAs)));
    }

    @Test
    @WithMockUser("admin")
    public void givenFoods_whenGetFoods_thenStatus200() throws Exception {
        createTestFood(1, "apple");
        createTestFood(2, "orange");
        service.refreshMV();

        // @formatter:off
        mvc.perform(get("/api/food").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("orange")))
                .andExpect(jsonPath("$[1].name", is("apple")));
        // @formatter:on
    }

    private void createTestFood(Integer id, String name) {
        Food food = new Food();
        food.setId(id);
        food.setName(name);
        repository.saveAndFlush(food);
    }

}
