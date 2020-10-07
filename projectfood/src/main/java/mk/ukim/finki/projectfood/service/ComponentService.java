package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.views.ComponentsShowView;

import java.util.List;
import java.util.Map;

public interface ComponentService {

    List<Component> getAllComponents();

    Component getComponent(Integer id);

    List<ComponentsShowView> getAllComponentsShow();

    ComponentsShowView getComponentShow(Integer id);

    Component getComponentByName(String name);

    Component updateCompoundId(Integer id, Integer compoundId);

    void mapComponentToCompounds();

    Map<Integer, Integer> countPossibleMappingsCompounds();

    void populateSameAs();

    String lookupExternal(String term);

    Map<Integer, Integer> countPossibleMappingsExternal();

    void refreshMV();

}
