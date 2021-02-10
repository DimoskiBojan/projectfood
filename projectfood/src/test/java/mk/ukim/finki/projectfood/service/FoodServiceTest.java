package mk.ukim.finki.projectfood.service;

import java.util.*;
import java.util.stream.Collectors;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.Foods;
import mk.ukim.finki.projectfood.model.events.FoodUpdatedEvent;
import mk.ukim.finki.projectfood.model.exceptions.FoodNotFoundException;
import mk.ukim.finki.projectfood.repository.FoodRepository;
import mk.ukim.finki.projectfood.repository.views.FoodsShowViewRepository;
import mk.ukim.finki.projectfood.service.impl.FoodServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FoodServiceTest {

    private FoodService foodService;

    @MockBean
    private FoodRepository foodRepository;

    @MockBean
    private FoodsShowViewRepository foodsShowViewRepository;

    @MockBean
    private FoodsService foodsService;

    @MockBean
    private ApplicationEventPublisher eventPublisher;

    @Before
    public void setUp() {
        foodService = new FoodServiceImpl(foodRepository, foodsShowViewRepository, eventPublisher, foodsService);

        Food apple = new Food();
        apple.setId(1);
        apple.setName("apple");
        apple.setCategory("Fruit and vegetables");
        apple.setSameAs("https://dbpedia.org/resource/Apple");
        Food lemon = new Food();
        lemon.setId(2);
        lemon.setName("lemon");
        lemon.setCategory("Fruit and vegetables");
        Food orange = new Food();
        orange.setId(3);
        orange.setName("orange");
        orange.setCategory("Fruit and vegetables");
        Food gibberish = new Food();
        gibberish.setId(4);
        gibberish.setName("gibberishonetwothree");
        gibberish.setCategory("gibberish category");
        Food potatoProducts = new Food();
        potatoProducts.setId(5);
        potatoProducts.setName("Potato products");
        potatoProducts.setCategory("Vegetable");

        List<Food> allFoods = Arrays.asList(apple, lemon, orange, gibberish, potatoProducts);
        Pageable pageable = PageRequest.of(0, 2);
        int startIdx = Math.min((int) pageable.getOffset(), allFoods.size());
        int endIdx = Math.min(startIdx + pageable.getPageSize(), allFoods.size());
        Page<Food> foodsPaged = new PageImpl<>(allFoods.subList(startIdx, endIdx), pageable, allFoods.size());

        Mockito.when(foodRepository.findByName(apple.getName())).thenReturn(apple);
        Mockito.when(foodRepository.findByName(lemon.getName())).thenReturn(lemon);
        Mockito.when(foodRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(foodRepository.findByCategory("Fruit and vegetables")).thenReturn(allFoods.stream()
                .filter(food -> food.getCategory().equals("Fruit and vegetables")).collect(Collectors.toList()));
        Mockito.when(foodRepository.findById(orange.getId())).thenReturn(Optional.of(orange));
        Mockito.when(foodRepository.findAll(pageable)).thenReturn(foodsPaged);
        Mockito.when(foodRepository.findAll()).thenReturn(allFoods);
        Mockito.when(foodRepository.findById(-99)).thenReturn(Optional.empty());
        Mockito.when(foodRepository.findById(1)).thenReturn(Optional.of(apple));
        Mockito.when(foodRepository.findById(2)).thenReturn(Optional.of(lemon));
        Mockito.when(foodRepository.findById(3)).thenReturn(Optional.of(orange));
        Mockito.when(foodRepository.save(Mockito.any(Food.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(foodRepository.saveAll(Mockito.any(List.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Foods foodb = new Foods();
        foodb.setId(1);
        foodb.setName("lemon");
        foodb.setCategory("Fruit and vegetables");
        foodb.setSameAs("https://dbpedia.org/resource/Lemon");
        Foods foodbNoSameAs = new Foods();
        foodbNoSameAs.setId(2);
        foodbNoSameAs.setName("orange");
        foodbNoSameAs.setCategory("Fruit and vegetables");
        Mockito.when(foodsService.getFood(1)).thenReturn(foodb);
        Mockito.when(foodsService.getFoodByName(foodb.getName())).thenReturn(foodb);
        Mockito.when(foodsService.getFood(2)).thenReturn(foodbNoSameAs);
        Mockito.when(foodsService.getFoodByName(foodbNoSameAs.getName())).thenReturn(foodbNoSameAs);
    }

    @Test
    public void whenValidName_thenFoodShouldBeFound() {
        String name = "apple";
        Food found = foodService.getFoodByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }

    @Test
    public void whenInValidName_thenFoodShouldNotBeFound() {
        Food fromDb = foodService.getFoodByName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
    public void whenValidId_thenFoodShouldBeFound() {
        Food fromDb = foodService.getFood(3);
        assertThat(fromDb.getName()).isEqualTo("orange");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenFoodShouldNotBeFound_ExceptionThrown() {
        assertThatThrownBy(() -> foodService.getFood(-99)).isInstanceOf(FoodNotFoundException.class);
        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void given5Foods_whengetAll_thenReturn5Records() {
        Food apple = new Food();
        apple.setId(1);
        apple.setName("apple");
        Food lemon = new Food();
        lemon.setId(2);
        lemon.setName("lemon");
        Food orange = new Food();
        orange.setId(3);
        orange.setName("orange");
        Food gibberish = new Food();
        gibberish.setId(4);
        gibberish.setName("gibberishonetwothree");
        Food potatoProducts = new Food();
        potatoProducts.setId(5);
        potatoProducts.setName("Potato products");


        List<Food> allFoods = foodService.getAllFoods();
        verifyFindAllIsCalledOnce();
        assertThat(allFoods).hasSize(5).extracting(Food::getName)
                .contains(apple.getName(), lemon.getName(), orange.getName(), gibberish.getName(), potatoProducts.getName());
    }

    @Test
    public void whenGetFoodsByCategory_thenReturnFoodListInGivenCategory() {
        List<Food> foodsByCategory = foodService.getFoodsByCategory("Fruit and vegetables");
        assertThat(foodsByCategory).hasSize(3).extracting(Food::getCategory).containsOnly("Fruit and vegetables");
    }

    @Test
    public void given5Foods_whengetAllPaged_thenReturn2Records5Total() {
        Food apple = new Food();
        apple.setId(1);
        apple.setName("apple");
        Food lemon = new Food();
        lemon.setId(2);
        lemon.setName("lemon");

        Page<Food> allFoodsPaged = foodService.getAllFoods(0, 2);
        assertThat(allFoodsPaged.getTotalElements()).isEqualTo(5);
        assertThat(allFoodsPaged.getTotalPages()).isEqualTo(3);
        assertThat(allFoodsPaged.getContent()).hasSize(2).extracting(Food::getName).contains(apple.getName(), lemon.getName());
    }

    @Test
    public void whenUpdateFoodSameAs_thenReturnUpdatedFood() {
        Food updatedFoodDb = foodService.updateFoodSameAs(1, "http://purl.obolibrary.org/obo/FOODON_00002473");
        verifyPublishEventIsCalled();
        assertThat(updatedFoodDb.getSameAs()).isEqualTo("http://purl.obolibrary.org/obo/FOODON_00002473");
    }

    @Test
    public void whenUpdateFoodSameAsSingleUrl_thenReturnUpdatedFood() {
        Food updatedFoodDbHasSameAs = foodService.updateFoodSameAsSingleUrl(1, "http://purl.obolibrary.org/obo/FOODON_00002473");
        verifyPublishEventIsCalled();
        assertThat(updatedFoodDbHasSameAs.getSameAs()).isEqualTo("https://dbpedia.org/resource/Apple;http://purl.obolibrary.org/obo/FOODON_00002473");

        Food updatedFoodDbNullSameAs = foodService.updateFoodSameAsSingleUrl(2, "http://purl.obolibrary.org/obo/FOODON_00002473");
        assertThat(updatedFoodDbNullSameAs.getSameAs()).isEqualTo(";http://purl.obolibrary.org/obo/FOODON_00002473");
    }

    @Test
    public void mapFoodToFoods() {
        List<Food> updatedFoods = foodService.mapFoodToFoods();
        assertThat(updatedFoods).isNotNull().isNotEmpty();
        assertThat(updatedFoods).anyMatch(food -> food.getFoodb_id() != null);
        assertThat(updatedFoods.stream().filter(food -> food.getName().equals("lemon")).findFirst().get().getSameAs())
                .isEqualTo(";https://dbpedia.org/resource/Lemon");
    }

    @Test
    public void whenUpdateFoodFooDBId_thenReturnUpdatedFood() {
        Food updatedFoodDbHasSameAs = foodService.updateFoodFooDBId(2, 1);
        verifyPublishEventIsCalled();
        assertThat(updatedFoodDbHasSameAs.getFoodb_id().getId()).isEqualTo(1);
        assertThat(updatedFoodDbHasSameAs.getSameAs()).isEqualTo(";https://dbpedia.org/resource/Lemon");
    }

    @Test
    public void whenLookupExternal_thenReturnString() {
        String result = foodService.lookupExternal("apple");
        assertThat(result).contains("snomedct").contains("foodon");
    }

    @Test
    public void whenCountPossibleMapping_thenReturnNumberOf() {
        Map<Integer, Integer> possibleMappings = foodService.countPossibleMappings();
        assertThat(possibleMappings).isNotEmpty();
    }

    @Test
    public void mapFood() {
        foodService.mapFoodToFOODON();
        foodService.mapFoodToSNOMEDCT();
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(foodRepository, VerificationModeFactory.times(1)).findByName(name);
        Mockito.reset(foodRepository);
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(foodRepository, VerificationModeFactory.times(1)).findById(Mockito.anyInt());
        Mockito.reset(foodRepository);
    }

    private void verifyFindAllIsCalledOnce() {
        Mockito.verify(foodRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(foodRepository);
    }

    private void verifyPublishEventIsCalled() {
        Mockito.verify(eventPublisher, VerificationModeFactory.atLeastOnce()).publishEvent(Mockito.any(FoodUpdatedEvent.class));
        Mockito.reset(eventPublisher);
    }
}
