package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Nutrients;
import mk.ukim.finki.projectfood.repository.NutrientsRepository;
import mk.ukim.finki.projectfood.service.NutrientsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutrientsServiceImpl implements NutrientsService {
    private final NutrientsRepository nutrientsRepository;

    public NutrientsServiceImpl(NutrientsRepository nutrientsRepository) {
        this.nutrientsRepository = nutrientsRepository;
    }


    @Override
    public List<Nutrients> getAllNutrients() {
        return nutrientsRepository.findAll();
    }
}
