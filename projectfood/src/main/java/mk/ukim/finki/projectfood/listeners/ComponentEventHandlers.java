package mk.ukim.finki.projectfood.listeners;

import mk.ukim.finki.projectfood.model.events.ComponentUpdatedEvent;
import mk.ukim.finki.projectfood.service.ComponentService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ComponentEventHandlers {
    private final ComponentService componentService;

    public ComponentEventHandlers(ComponentService componentService) {
        this.componentService = componentService;
    }

    @EventListener
    public void onFoodUpdated(ComponentUpdatedEvent event) {
        System.out.println("Updated " + ((java.awt.Component)event.getSource()).getName());
        this.componentService.refreshMV();
    }
}
