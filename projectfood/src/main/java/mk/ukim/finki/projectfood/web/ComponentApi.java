package mk.ukim.finki.projectfood.web;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.Compounds;
import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.views.ComponentsShowView;
import mk.ukim.finki.projectfood.service.ComponentService;
import mk.ukim.finki.projectfood.service.CompoundsService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/component", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ComponentApi {
    private final ComponentService componentService;
    private final CompoundsService compoundsService;

    public ComponentApi(ComponentService componentService, CompoundsService compoundsService) {
        this.componentService = componentService;
        this.compoundsService = compoundsService;
    }

    @GetMapping
    public List<ComponentsShowView> getAllComponents() { return componentService.getAllComponentsShow(); }

    @GetMapping("/{id}")
    public ComponentsShowView getComponent(@PathVariable("id") Integer id) {
        return componentService.getComponentShow(id);
    }

    @GetMapping(path = "/lookup/compounds", params = "name")
    public List<Compounds> lookupCompounds(String name) {
        return compoundsService.lookupCompoundsByName(name);
    }

//    @GetMapping("/lookup/compounds/count-possible-mappings")
//    public Map<Integer, Integer> countPossibleMappingsCompounds() {
//        return componentService.countPossibleMappingsCompounds();
//    }

    @GetMapping("/lookup/external")
    public String lookupExternal(String term) {
        return componentService.lookupExternal(term);
    }

//    @GetMapping("/lookup/external/count-possible-mappings")
//    public Map<Integer, Integer> countPossibleMappingsExternal() {
//        return componentService.countPossibleMappingsExternal();
//    }

    @PatchMapping("/{id}")
    public Component updateCompoundId(@PathVariable("id") Integer id,
                                 @RequestParam Integer compoundId) {
        return componentService.updateCompoundId(id, compoundId);
    }
}
