package com.mercadolibre.mutantes.service.Implentation;

import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.repository.DnaRepository;
import com.mercadolibre.mutantes.service.MutantDnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MutantDnaServiceIm  implements MutantDnaService {

    @Autowired
    private DnaRepository dnaRepository;

    @CacheEvict(value="stats", allEntries=true)
    public void addADnaSequence(String type, String dna){
        dnaRepository.addADnaSequence(type, dna);
    }

    @Cacheable("stats")
    public DNAStat getDnaStats() {
        return dnaRepository.getDnaStats();
    }
}
