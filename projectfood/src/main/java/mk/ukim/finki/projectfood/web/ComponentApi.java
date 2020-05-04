package mk.ukim.finki.projectfood.web;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.service.ComponentService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/component", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ComponentApi {
    private final ComponentService componentService;

    public ComponentApi(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    public List<Component> getAllComponents() { return componentService.getAllComponents(); }

    @GetMapping("/{id}")
    public Component getComponent(@PathVariable("id") Integer id) {
        return componentService.getComponent(id);
    }
}
