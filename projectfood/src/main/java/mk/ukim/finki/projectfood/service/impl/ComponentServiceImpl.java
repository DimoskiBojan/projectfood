package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.Compounds;
import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.Foods;
import mk.ukim.finki.projectfood.model.exceptions.ComponentNotFoundException;
import mk.ukim.finki.projectfood.model.views.ComponentsShowView;
import mk.ukim.finki.projectfood.repository.ComponentRepository;
import mk.ukim.finki.projectfood.repository.views.ComponentsShowViewRepository;
import mk.ukim.finki.projectfood.service.ComponentService;
import mk.ukim.finki.projectfood.service.CompoundsService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {
    private final ComponentRepository componentRepository;
    private final ComponentsShowViewRepository componentsShowViewRepository;
    private final ApplicationEventPublisher eventPublisher;

    private final CompoundsService compoundsService;

    public ComponentServiceImpl(ComponentRepository componentRepository,
                                ComponentsShowViewRepository componentsShowViewRepository,
                                ApplicationEventPublisher eventPublisher,
                                CompoundsService compoundsService) {
        this.componentRepository = componentRepository;
        this.componentsShowViewRepository = componentsShowViewRepository;
        this.eventPublisher = eventPublisher;
        this.compoundsService = compoundsService;
    }

    @Override
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    @Override
    public Component getComponent(Integer id) {
        return componentRepository.findById(id).orElseThrow(ComponentNotFoundException::new);
    }

    @Override
    public List<ComponentsShowView> getAllComponentsShow() {
        return componentsShowViewRepository.fetchAll();
    }

    @Override
    public ComponentsShowView getComponentShow(Integer id) {
        return componentsShowViewRepository.findById(id).orElseThrow(ComponentNotFoundException::new);
    }

    @Override
    public Component getComponentByName(String name) {
        return componentRepository.findByName(name);
    }

    @Override
    public void mapComponentToCompounds() {
        List<Component> components = this.getAllComponents();
        components.forEach(component -> {
            Compounds compound = compoundsService.getCompoundsByName(component.getName());
            if(compound != null){
                component.setCompound(compound);
            }
        });
        componentRepository.saveAll(components);
    }

    @Override
    public void refreshMV() {
        componentsShowViewRepository.refreshMV();
    }
}
