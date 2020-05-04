package mk.ukim.finki.projectfood.service.impl;

import mk.ukim.finki.projectfood.model.Compounds;
import mk.ukim.finki.projectfood.repository.CompoundsRepository;
import mk.ukim.finki.projectfood.service.CompoundsService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Compounds getCompoundsByName(String name) {
        return compoundsRepository.findByName(name);
    }
}
