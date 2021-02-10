package mk.ukim.finki.projectfood.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.Foods;
import mk.ukim.finki.projectfood.model.events.ComponentUpdatedEvent;
import mk.ukim.finki.projectfood.model.events.FoodUpdatedEvent;
import mk.ukim.finki.projectfood.model.exceptions.FoodNotFoundException;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        return foodsShowViewRepository.fetchAll();
    }

    @Override
    public List<Food> getFoodsByCategory(String category) {
        return foodRepository.findByCategory(category);
    }

    @Override
    public Page<Food> getAllFoods(int page, int size) {
        return this.foodRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Food getFood(Integer id) {
        return foodRepository.findById(id).orElseThrow(FoodNotFoundException::new);
    }

    @Override
    public FoodsShowView getFoodShow(Integer id) { return foodsShowViewRepository.findById(id).orElseThrow(FoodNotFoundException::new); }

    @Override
    public Food getFoodByName(String name) {
        return foodRepository.findByName(name);
    }

    // sameAs should be in format ;url;url
    @Override
    public Food updateFoodSameAs(Integer id, String sameAs) {
        Food food = this.getFood(id);
        food.setSameAs(sameAs);

        foodRepository.save(food);
        eventPublisher.publishEvent(new FoodUpdatedEvent(food));

        return food;
    }

    @Override
    public Food updateFoodSameAsSingleUrl(Integer id, String sameAs) {
        Food food = this.getFood(id);
        checkAndSetSameAsSingleUrl(food, sameAs);

        foodRepository.save(food);
        eventPublisher.publishEvent(new FoodUpdatedEvent(food));

        return food;
    }

    @Override
    public Food updateFoodFooDBId(Integer id, Integer foodbId) {
        Food food = this.getFood(id);
        Foods foodb = foodsService.getFood(foodbId);
        food.setFoodb_id(foodb);
        String foodbSameAs = foodb.getSameAs();
        checkAndSetSameAsSingleUrl(food, foodbSameAs);

        foodRepository.save(food);
        eventPublisher.publishEvent(new FoodUpdatedEvent(food));

        return food;
    }

    @Override
    public List<Food> mapFoodToFoods(){
        List<Food> foods = this.getAllFoods();
        foods.forEach(food -> {
            Foods foodb = foodsService.getFoodByName(food.getName());
            if(foodb != null){
                food.setFoodb_id(foodb);
                String foodbSameAs = foodb.getSameAs();
                checkAndSetSameAsSingleUrl(food, foodbSameAs);
            }
        });
        return foodRepository.saveAll(foods);
    }

    @Override
    public void mapFoodToFOODON() {

        String foodON = "http://www.ontobee.org/api/search?ontology=FOODON&term=";

        List<Food> foodList = foodRepository.findAll();
        foodList.forEach(food -> {
            String term = food.getName();
            JSONArray json = HttpUtils.getJSONArrayFromUrl(foodON.concat(term));
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
                    if(i < json.length()) {
                        System.out.println(term + ": " + json.getJSONObject(i).get("iri"));
                        this.updateFoodSameAsSingleUrl(food.getId(), (String) json.getJSONObject(i).get("iri"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void mapFoodToSNOMEDCT() {

        String SNOMEDCT = "http://data.bioontology.org/search?ontologies=SNOMEDCT&pagesize=10&apikey=d01734b9-8dc5-414c-bef1-d7cd327ccc99&q=";

        List<Food> foodList = foodRepository.findAll();
        foodList.forEach(food -> {
            String term = food.getName();
            JSONArray json = null;
            try {
                json = Objects.requireNonNull(HttpUtils.getJSONObjectFromUrl(SNOMEDCT.concat(term))).getJSONArray("collection");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(json != null && json.length() != 0) {
                int i;
                for (i = 0; i < json.length(); ++i) {
                    JSONObject entry = null;
                    try {
                        entry = json.getJSONObject(i);
                        String label = entry.getString("prefLabel");
                        if (label.equalsIgnoreCase(term)) {
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    if(i < json.length()) {
                        System.out.println(term + ": " + json.getJSONObject(i).getJSONObject("links").getString("self"));
                        this.updateFoodSameAsSingleUrl(food.getId(), json.getJSONObject(i).getJSONObject("links").getString("self"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String lookupExternal(String term) {
        String foodON = "http://www.ontobee.org/api/search?ontology=FOODON&term=";
        String SNOMEDCT = "http://data.bioontology.org/search?ontologies=SNOMEDCT&pagesize=10&apikey=d01734b9-8dc5-414c-bef1-d7cd327ccc99&q=";
        String dbPedia = "https://lookup.dbpedia.org/api/search?format=JSON&query=";

        JSONArray json;
        JSONArray snomedArray = new JSONArray();
        JSONArray foodOnArray = new JSONArray();
        JSONArray dbpediaArray = new JSONArray();
        JSONObject result = new JSONObject();
        try {
            //SNOMEDCT
            json = Objects.requireNonNull(HttpUtils.getJSONObjectFromUrl(SNOMEDCT.concat(term))).getJSONArray("collection");
            if(json != null && json.length() != 0) {
                int i;
                for (i = 0; i < json.length(); ++i) {
                    JSONObject entry;
                    entry = json.getJSONObject(i);
                    JSONObject obj = new JSONObject();
                    obj.put("label", entry.getString("prefLabel"));
                    obj.put("uri", entry.getJSONObject("links").getString("self"));

                    snomedArray.put(obj);
                }
            }

            //FOODON
            json = HttpUtils.getJSONArrayFromUrl(foodON.concat(term));
            if(json != null && json.length() != 0) {
                int i;
                for (i = 0; i < json.length(); ++i) {
                    JSONObject entry;
                    entry = json.getJSONObject(i);
                    String label = entry.getString("value");
                    String uri = (String) entry.get("iri");
                    JSONObject obj = new JSONObject();
                    obj.put("label", entry.getString("value"));
                    obj.put("uri", (String) entry.get("iri"));

                    foodOnArray.put(obj);
                }

            }

            //DBPedia
//            json = Objects.requireNonNull(HttpUtils.getJSONObjectFromUrl(dbPedia.concat(term))).getJSONArray("docs");
//            if(json != null && json.length() != 0) {
//                int i;
//                for (i = 0; i < json.length(); ++i) {
//                    JSONObject entry;
//                    entry = json.getJSONObject(i);
//                    String label = entry.getJSONArray("label").getString(0);
//                    label = label.replaceAll("<[^>]*>","");
//                    JSONObject obj = new JSONObject();
//                    obj.put("label", label);
//                    obj.put("uri", entry.getJSONArray("resource").getString(0));
//                    if(entry.has("comment"))
//                        obj.put("comment", entry.getJSONArray("comment").getString(0));
//                    dbpediaArray.put(obj);
//                }
//            }

            result.put("snomedct", snomedArray);
            result.put("foodon", foodOnArray);
//            result.put("dbpedia", dbpediaArray);

            return result.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Integer> countPossibleMappings() {
        Map<Integer, Integer> possibleMappingNumber = new HashMap<>();
        List<Food> foodList = foodRepository.findAll();
        foodList.forEach(food -> {
            String term = food.getName();
            Integer possibleMappings = possibleMappings(term);
            if(possibleMappings > 30) possibleMappings = 30;
            possibleMappingNumber.computeIfAbsent(possibleMappings, k -> 0);
            possibleMappingNumber.computeIfPresent(possibleMappings, (key, val) -> val + 1);
        });
        return possibleMappingNumber;
    }

    public Integer possibleMappings(String term) {
        String foodON = "http://www.ontobee.org/api/search?ontology=FOODON&term=";
        String SNOMEDCT = "http://data.bioontology.org/search?ontologies=SNOMEDCT&pagesize=10&apikey=d01734b9-8dc5-414c-bef1-d7cd327ccc99&q=";
        String dbPedia = "https://lookup.dbpedia.org/api/search?format=JSON&query=";

        JSONArray json;
        Integer result = 0;
        try {
            //SNOMEDCT
            json = Objects.requireNonNull(HttpUtils.getJSONObjectFromUrl(SNOMEDCT.concat(term))).getJSONArray("collection");
            if(json != null) {
                result += json.length();
            }

            //FOODON
            json = HttpUtils.getJSONArrayFromUrl(foodON.concat(term));
            if(json != null) {
                result += json.length();
            }

            //DBPedia
//            json = Objects.requireNonNull(HttpUtils.getJSONObjectFromUrl(dbPedia.concat(term))).getJSONArray("docs");
//            if(json != null) {
//                result += json.length();
//            }

            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void refreshMV() {
        foodsShowViewRepository.refreshMV();
    }

    private void checkAndSetSameAsSingleUrl(Food food, String sameAs) {
        if (food.getSameAs() != null && !food.getSameAs().isEmpty()) {
            if(!food.getSameAs().contains(sameAs)) {
                String newSameAs = food.getSameAs().concat(";" + sameAs);
                food.setSameAs(newSameAs);
            }
        } else {
            food.setSameAs(";" + sameAs);
        }
    }
}
