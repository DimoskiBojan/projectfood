package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Compounds;
import mk.ukim.finki.projectfood.model.exceptions.CompoundNotFoundException;
import mk.ukim.finki.projectfood.repository.CompoundsRepository;
import mk.ukim.finki.projectfood.service.CompoundsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompoundsServiceImpl implements CompoundsService {

    private final CompoundsRepository compoundsRepository;

    public CompoundsServiceImpl(CompoundsRepository compoundsRepository){
        this.compoundsRepository = compoundsRepository;
    }

    @Override
    public List<Compounds> getAllCompounds() {
        return compoundsRepository.findAll();
    }

    @Override
    public Compounds getCompound(Integer id) {
        return compoundsRepository.findById(id).orElseThrow(CompoundNotFoundException::new);
    }

    @Override
    public Compounds getCompoundsByName(String name) {
        return compoundsRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Compounds> lookupCompoundsByName(String name) {
        List<Compounds> compounds = getAllCompounds();
        if (name.length() > 1) {
            return compounds.stream().filter(compound -> {
                        if(compound.getMoldb_formula() != null){
                            return compound.getName().equalsIgnoreCase(name)
                                    || compound.getName().toLowerCase().startsWith(name.toLowerCase())
                                    || compound.getName().matches(".*\\(.*" + name + ".*\\)")
                                    || compound.getName().toLowerCase().endsWith(name.toLowerCase())
                                    || compound.getMoldb_formula().equalsIgnoreCase(name);
                        } else {
                            return compound.getName().equalsIgnoreCase(name)
                                    || compound.getName().toLowerCase().startsWith(name.toLowerCase())
                                    || compound.getName().matches(".*\\(.*" + name + ".*\\)")
                                    || compound.getName().toLowerCase().endsWith(name.toLowerCase());
                        }
                    }
            ).collect(Collectors.toList());
        } else {
            return compounds.stream().filter(compound ->
                    compound.getName().equalsIgnoreCase(name)
                            || compound.getName().toLowerCase().startsWith(name.toLowerCase())
                            || compound.getName().matches(".*\\(.*" + name + ".*\\)")
            ).filter(compound ->
                    compound.getMoldb_formula() == null
                            || compound.getMoldb_formula().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

    }
}
