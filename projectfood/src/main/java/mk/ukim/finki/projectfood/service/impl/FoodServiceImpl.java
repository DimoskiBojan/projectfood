package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.Foods;
import mk.ukim.finki.projectfood.model.events.FoodUpdatedEvent;
import mk.ukim.finki.projectfood.model.views.FoodsShowView;
import mk.ukim.finki.projectfood.repository.FoodRepository;
import mk.ukim.finki.projectfood.repository.views.FoodsShowViewRepository;
import mk.ukim.finki.projectfood.service.FoodService;
import mk.ukim.finki.projectfood.service.FoodsService;
import mk.ukim.finki.projectfood.util.HttpUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final FoodsShowViewRepository foodsShowViewRepository;
    private final ApplicationEventPublisher eventPublisher;

    private final FoodsService foodsService;

    public FoodServiceImpl(FoodRepository foodRepository,
                           FoodsShowViewRepository foodsShowViewRepository,
                           ApplicationEventPublisher eventPublisher,
                           FoodsService foodsService) {
        this.foodRepository = foodRepository;
        this.foodsShowViewRepository = foodsShowViewRepository;
        this.eventPublisher = eventPublisher;
        this.foodsService = foodsService;
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public List<FoodsShowView> getAllFoodsShow() {
        return foodsShowViewRepository.findAll();
    }

    @Override
    public Page<Food> getAllFoods(int page, int size) {
        return this.foodRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Food getFood(Integer id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public FoodsShowView getFoodShow(Integer id) {
        return foodsShowViewRepository.findById(id).orElse(null);
    }

    @Override
    public Food getFoodByName(String name) {
        return foodRepository.findByName(name);
    }

    // sameAs should be in format ;url;url
    @Override
    public Food updateFoodSameAs(Integer id, String sameAs) {
        Food food = this.getFood(id);
        // Code for updating single sameAs url
        /*if (food.getSameAs() != null && !food.getSameAs().isEmpty()) {
            if(!food.getSameAs().contains(sameAs)) {
                String newSameAs = food.getSameAs().concat(";" + sameAs);
                food.setSameAs(newSameAs);
            }
        } else {
            food.setSameAs(";" + sameAs);
        }*/

        food.setSameAs(sameAs);
        foodRepository.save(food);
        eventPublisher.publishEvent(new FoodUpdatedEvent(food));

        return food;
    }

    @Override
    public void mapFoodToFoods(){
        List<Food> foods = this.getAllFoods();
        foods.forEach(food -> {
            Foods foodb = foodsService.getFoodByName(food.getName());
            if(foodb != null){
                food.setFoodb_id(foodb);
            }
        });
        foodRepository.saveAll(foods);
    }

    @Override
    public void mapFoodToFOODON() {

        String foodON = "http://www.ontobee.org/api/search?ontology=FOODON&term=";

        List<Food> foodList = foodRepository.findAll();
        foodList.forEach(food -> {
            String term = food.getName();
            JSONArray json = HttpUtils.getJSONFromUrl(foodON.concat(term));
            if(json != null && json.length() != 0) {
                int i;
                for (i = 0; i < json.length(); ++i) {
                    JSONObject entry = null;
                    try {
                        entry = json.getJSONObject(i);
                        String value = entry.getString("value");
                        if (value.equalsIgnoreCase(term) || value.equalsIgnoreCase(term + " (whole, raw)")) {
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    if(i < json.length())
                    System.out.println(term + ": " + json.getJSONObject(i).get("iri"));
                    this.updateFoodSameAs(food.getId(), (String) json.getJSONObject(i).get("iri"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    @Override
    public void refreshMV() {
        foodsShowViewRepository.refreshMV();
    }
}