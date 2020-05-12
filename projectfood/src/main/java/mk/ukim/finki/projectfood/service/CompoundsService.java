package mk.ukim.finki.projectfood.service;

import mk.ukim.finki.projectfood.model.Compounds;

import java.util.List;

public interface CompoundsService {

    List<Compounds> getAllCompounds();

    Compounds getCompound(Integer id);

    Compounds getCompoundsByName(String name);

    List<Compounds> lookupCompoundsByName(String name);
}
