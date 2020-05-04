package mk.ukim.finki.projectfood.listeners;

import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.events.FoodUpdatedEvent;
import mk.ukim.finki.projectfood.service.FoodService;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class FoodEventHandlers {
    private final FoodService foodService;

    public FoodEventHandlers(FoodService foodService) {
        this.foodService = foodService;
    }

    @EventListener
    public void onFoodUpdated(FoodUpdatedEvent event) {
        System.out.println("Updated " + ((Food)event.getSource()).getName());
        this.foodService.refreshMV();
    }

}
