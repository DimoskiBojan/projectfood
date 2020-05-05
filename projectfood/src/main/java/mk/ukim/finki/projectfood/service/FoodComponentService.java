package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.FoodComponent;

import java.util.List;

public interface FoodComponentService {
    List<FoodComponent> getAllFoodComponentsAndWasteStreams();
}
