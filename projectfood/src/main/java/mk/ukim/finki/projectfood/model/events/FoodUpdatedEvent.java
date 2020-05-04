package mk.ukim.finki.projectfood.model.events;

import lombok.Getter;
import mk.ukim.finki.projectfood.model.Food;
import org.springframework.context.ApplicationEvent;

@Getter
public class FoodUpdatedEvent extends ApplicationEvent {

    public FoodUpdatedEvent(Food source) {
        super(source);
    }
}
