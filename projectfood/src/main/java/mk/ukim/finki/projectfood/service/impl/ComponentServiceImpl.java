package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Component;
import mk.ukim.finki.projectfood.model.Compounds;
import mk.ukim.finki.projectfood.model.Food;
import mk.ukim.finki.projectfood.model.Foods;
import mk.ukim.finki.projectfood.model.events.ComponentUpdatedEvent;
import mk.ukim.finki.projectfood.model.events.FoodUpdatedEvent;
import mk.ukim.finki.projectfood.model.exceptions.ComponentNotFoundException;
import mk.ukim.finki.projectfood.model.views.ComponentsShowView;
import mk.ukim.finki.projectfood.repository.ComponentRepository;
import mk.ukim.finki.projectfood.repository.views.ComponentsShowViewRepository;
import mk.ukim.finki.projectfood.service.ComponentService;
import mk.ukim.finki.projectfood.service.CompoundsService;
import mk.ukim.finki.projectfood.util.HttpUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Component updateCompoundId(Integer id, Integer compoundId) {
        Component component = this.getComponent(id);
        component.setCompound(compoundsService.getCompound(compoundId));

        componentRepository.save(component);
        eventPublisher.publishEvent(new ComponentUpdatedEvent(component));

        return component;
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
    public void populateSameAs() {
        String dbpedia = "http://dbpedia.org/resource/";
        String hmdb = "https://hmdb.ca/metabolites/";
        String drugbank = "https://www.drugbank.ca/drugs/";
        String phenolExplorer = "http://phenol-explorer.eu/compounds/";
        String pubchem = "https://pubchem.ncbi.nlm.nih.gov/compound/";
        String chebi = "https://www.ebi.ac.uk/chebi/searchId.do?chebiId=";
        String chembl = "https://www.ebi.ac.uk/chembl/api/data/molecule/";
        String kegg = "https://www.genome.jp/dbget-bin/www_bget?cpd:";

        List<Component> components = componentRepository.findAll();
        components.stream().filter(component -> component.getCompound() != null).forEach(component -> {
            StringBuilder sb = new StringBuilder();

            // DBpedia
            if(component.getCompound().getWikipedia_id() != null) {
                String wikipediaIdUnderscores = component.getCompound().getWikipedia_id().replace(' ', '_');
                String url = dbpedia.concat(wikipediaIdUnderscores);
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // HMDB
            if(component.getCompound().getHmdb_id() != null) {
                String url = hmdb.concat(component.getCompound().getHmdb_id());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // DrugBank
            if(component.getCompound().getDrugbank_id() != null) {
                String url = drugbank.concat(component.getCompound().getDrugbank_id());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Phenol-Explorer
            if(component.getCompound().getPhenolexplorer_id() != null) {
                String url = phenolExplorer.concat(component.getCompound().getPhenolexplorer_id().toString());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // PubChem
            if(component.getCompound().getPubchem_compound_id() != null) {
                String url = pubchem.concat(component.getCompound().getPubchem_compound_id());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // ChEBI
            if(component.getCompound().getChebi_id() != null) {
                String url = chebi.concat(component.getCompound().getChebi_id());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // ChEMBL
            if(component.getCompound().getChembl_id() != null) {
                String url = chembl.concat(component.getCompound().getChembl_id());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // KEGG
            if(component.getCompound().getKegg_compound_id() != null) {
                String url = kegg.concat(component.getCompound().getKegg_compound_id());
                try {
                    if (HttpUtils.getResponseStatusFromHttpUrl(url) >= 400) {
                        System.out.println("Broken link: " + url);
                    } else {
                        sb.append(";").append(url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String sameAs = sb.toString();
            if(!sameAs.isEmpty()) {
                component.setSameAs(sb.toString());
            }
        });

        componentRepository.saveAll(components);
    }

    @Override
    public void refreshMV() {
        componentsShowViewRepository.refreshMV();
    }
}
