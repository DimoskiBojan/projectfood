package mk.ukim.finki.projectfood.web;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.Nutrients;
import mk.ukim.finki.projectfood.service.NutrientsService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/nutrients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class NutrientsApi {
    private final NutrientsService nutrientsService;

    public NutrientsApi(NutrientsService nutrientsService) {
        this.nutrientsService = nutrientsService;
    }

    @GetMapping
    public List<Nutrients> getAllNutrients() { return nutrientsService.getAllNutrients(); }

    @GetMapping("/{id}")
    public Nutrients getNutrient(@PathVariable("id") Integer id) {
        return nutrientsService.getNutrient(id);
    }
}
