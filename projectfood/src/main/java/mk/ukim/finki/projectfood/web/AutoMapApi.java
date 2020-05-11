package mk.ukim.finki.projectfood.web;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.service.ComponentService;
import mk.ukim.finki.projectfood.service.FoodService;
import mk.ukim.finki.projectfood.service.FoodsService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/automap", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class AutoMapApi {
    private final FoodService foodService;
    private final FoodsService foodsService;
    private final ComponentService componentService;

    public AutoMapApi(FoodService foodService, FoodsService foodsService, ComponentService componentService) {
        this.foodService = foodService;
        this.foodsService = foodsService;
        this.componentService = componentService;
    }

    @GetMapping("/food-external")
    public void autoMapFoodToExternal() { foodService.mapFoodToFOODON(); }

    @GetMapping("/food-foods")
    public void autoMapFoodToFoods() { foodService.mapFoodToFoods(); }

    @GetMapping("/foods-dbpedia")
    public void autoMapFoodsToDbpedia() { foodsService.populateSameAs(); }

    @GetMapping("/component-compounds")
    public void autoMapComponentToCompounds() { componentService.mapComponentToCompounds(); }

    @GetMapping("/component-external")
    public void autoMapComponentToExternal() { componentService.populateSameAs(); }

}
