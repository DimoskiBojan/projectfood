package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Nutrients;

import java.util.List;

public interface NutrientsService {
    List<Nutrients> getAllNutrients();

    Nutrients getNutrient(Integer id);
}
