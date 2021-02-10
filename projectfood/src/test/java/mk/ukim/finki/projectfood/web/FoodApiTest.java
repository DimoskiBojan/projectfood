package mk.ukim.finki.projectfood.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.views.FoodsShowView;
import mk.ukim.finki.projectfood.service.FoodComponentService;
import mk.ukim.finki.projectfood.service.FoodService;
import mk.ukim.finki.projectfood.user.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(value = FoodApi.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class FoodApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FoodService foodService;

    @MockBean
    private FoodComponentService foodComponentService;

    @MockBean private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @WithMockUser("admin")
    public void whenPatchSameAs_thenUpdateFoodSameAs() throws Exception {
        String sameAs = "http://dbpedia.org/resource/Apple";
        Food apple = new Food();
        apple.setId(1);
        apple.setName("apple");
        apple.setSameAs(sameAs);

        given(foodService.updateFoodSameAs(1, sameAs)).willReturn(apple);

        mvc.perform(patch("/api/food/1/sameas").param("sameAs", sameAs))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sameAs", is(sameAs)));
        verify(foodService, VerificationModeFactory.times(1)).updateFoodSameAs(1, sameAs);
        reset(foodService);
    }

    @Test
    public void givenFoods_whenGetAllFoods_thenReturnJsonArray() throws Exception {
        FoodsShowView apple = new FoodsShowView();
        apple.setId(1);
        apple.setName("apple");
        FoodsShowView lemon = new FoodsShowView();
        lemon.setId(2);
        lemon.setName("lemon");
        FoodsShowView orange = new FoodsShowView();
        orange.setId(3);
        orange.setName("orange");

        List<FoodsShowView> allFoods = Arrays.asList(apple, lemon, orange);

        given(foodService.getAllFoodsShow()).willReturn(allFoods);

        mvc.perform(get("/api/food").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(apple.getName())))
                .andExpect(jsonPath("$[1].name", is(lemon.getName())))
                .andExpect(jsonPath("$[2].name", is(orange.getName())));
        verify(foodService, VerificationModeFactory.times(1)).getAllFoodsShow();
        reset(foodService);
    }

}
