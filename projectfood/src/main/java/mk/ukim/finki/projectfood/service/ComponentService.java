package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Component;

import java.util.List;

public interface ComponentService {

    List<Component> getAllComponents();

    Component getComponent(Integer id);

    Component getComponentByName(String name);

}
