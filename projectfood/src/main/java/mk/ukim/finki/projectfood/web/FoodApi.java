package mk.ukim.finki.projectfood.web;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.FoodComponent;
import mk.ukim.finki.projectfood.model.views.FoodsShowView;
import mk.ukim.finki.projectfood.service.FoodComponentService;
import mk.ukim.finki.projectfood.service.FoodService;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/food", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class FoodApi {

    private final FoodService foodService;

    private final FoodComponentService foodComponentService;

    public FoodApi(FoodService foodService, FoodComponentService foodComponentService) {
        this.foodService = foodService;
        this.foodComponentService = foodComponentService;
    }

    @GetMapping
    public List<FoodsShowView> getAllFoods() { return foodService.getAllFoodsShow(); }

    @GetMapping(params = "category")
    public List<Food> getFoodsByCategory(String category) { return foodService.getFoodsByCategory(category); }

    @GetMapping(headers = "page")
    public Page<Food> getAllFoods(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size) {
        return foodService.getAllFoods(page, size);
    }

    @GetMapping("/{id}")
    public FoodsShowView getFood(@PathVariable("id") Integer id) {
        return foodService.getFoodShow(id);
    }

    @PatchMapping(value = "/{id}/sameas")
    public Food updateFoodSameAs(@PathVariable("id") Integer id,
                      @RequestParam String sameAs) {
        return foodService.updateFoodSameAs(id, sameAs);
    }

    @PatchMapping(value = "/{id}/foodb-id")
    public Food updateFoodFooDBId(@PathVariable("id") Integer id,
                                 @RequestParam Integer foodbId) {
        return foodService.updateFoodFooDBId(id, foodbId);
    }

    @GetMapping("/lookup/external")
    public String lookupExternal(String term) {
        return foodService.lookupExternal(term);
    }

//    @GetMapping("/lookup/count-possible-mappings")
//    public Map<Integer, Integer> countPossibleMappings() {
//        return foodService.countPossibleMappings();
//    }

    @GetMapping("/streams")
    public List<FoodComponent> getAllFoodComponentsAndWasteStreams() {
        return foodComponentService.getAllFoodComponentsAndWasteStreams();
    }
}
