package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.repository.ComponentRepository;
import mk.ukim.finki.projectfood.service.ComponentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {
    private final ComponentRepository componentRepository;

    public ComponentServiceImpl(ComponentRepository componentRepository){
        this.componentRepository = componentRepository;
    }

    @Override
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    @Override
    public Component getComponent(Integer id) {
        return componentRepository.findById(id).orElse(null);
    }

    @Override
    public Component getComponentByName(String name) {
        return componentRepository.findByName(name);
    }
}
