package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.model.DnaSequence;
import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.repository.DnaSequenceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MutantStatService {

    private static Logger logger = LogManager.getLogger("MutantController");

    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    @Cacheable("stats")
    public DNAStat getDnaStats() {
        DNAStat dnaStat = dnaSequenceRepository.getDnaStats();
        logger.info("MutantStatService.getDnaStats. Body: " + dnaStat.print());
        return dnaStat;
    }

    @CacheEvict(value="stats", allEntries=true)
    public void update(DnaSequence dnaSequence){
        dnaSequenceRepository.update(dnaSequence);
    }
}
