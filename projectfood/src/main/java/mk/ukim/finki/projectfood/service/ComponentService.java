package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.views.ComponentsShowView;

import java.util.List;

public interface ComponentService {

    List<Component> getAllComponents();

    Component getComponent(Integer id);

    List<ComponentsShowView> getAllComponentsShow();

    ComponentsShowView getComponentShow(Integer id);

    Component getComponentByName(String name);

    void mapComponentToCompounds();

    void refreshMV();

}
