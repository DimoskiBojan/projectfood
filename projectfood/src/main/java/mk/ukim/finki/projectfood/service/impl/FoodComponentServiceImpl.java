package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.FoodComponent;
import mk.ukim.finki.projectfood.repository.FoodComponentRepository;
import mk.ukim.finki.projectfood.service.FoodComponentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodComponentServiceImpl implements FoodComponentService {
    private final FoodComponentRepository foodComponentRepository;

    public FoodComponentServiceImpl(FoodComponentRepository foodComponentRepository) {
        this.foodComponentRepository = foodComponentRepository;
    }

    @Override
    public List<FoodComponent> getAllFoodComponentsAndWasteStreams() {
        return foodComponentRepository.findAll();
    }
}
