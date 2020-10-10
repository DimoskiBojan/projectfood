package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Compounds;
import mk.ukim.finki.projectfood.model.Foods;
import mk.ukim.finki.projectfood.model.exceptions.CompoundNotFoundException;
import mk.ukim.finki.projectfood.repository.FoodsRepository;
import mk.ukim.finki.projectfood.service.FoodsService;
import mk.ukim.finki.projectfood.util.HttpUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class FoodsServiceImpl implements FoodsService {

    private final FoodsRepository foodsRepository;

    public FoodsServiceImpl(FoodsRepository foodsRepository) {
        this.foodsRepository = foodsRepository;
    }

    @Override
    public List<Foods> getAllFoods() {
        return foodsRepository.findAll();
    }

    @Override
    public Foods getFood(Integer id) {
        return foodsRepository.findById(id).orElseThrow(CompoundNotFoundException::new);
    }

    @Override
    public Foods getFoodByName(String name) {
        List<Foods> foods = this.getAllFoods();
        final Foods[] match = {null};
        foods.forEach(food -> {
            if(food.getName().equalsIgnoreCase(name)){
                match[0] = food;
            } else if(food.getName().matches(".*\\(.*" + name + ".*\\)")) {
                match[0] = food;
            }
        });

        return match[0];
    }

    @Override
    public void populateSameAs() {
       String dbpedia = "http://dbpedia.org/resource/";

       List<Foods> foods = foodsRepository.findAll();
       foods.forEach(food -> {
           if(food.getWikipedia_id() != null) {
               String wikipediaIdUnderscores = food.getWikipedia_id().replace(' ', '_');
               String url = dbpedia.concat(wikipediaIdUnderscores);
               try {
                   if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                       System.out.println("Broken link: " + url);
                   } else {
                        food.setSameAs(url);
                   }
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        });
       foodsRepository.saveAll(foods);
    }
}
