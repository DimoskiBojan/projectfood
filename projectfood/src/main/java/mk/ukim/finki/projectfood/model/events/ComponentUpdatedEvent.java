package mk.ukim.finki.projectfood.model.events;

import lombok.Getter;
import mk.ukim.finki.projectfood.model.Component;
import org.springframework.context.ApplicationEvent;

@Getter
public class ComponentUpdatedEvent extends ApplicationEvent {

    public ComponentUpdatedEvent(Component source) {
        super(source);
    }
}
