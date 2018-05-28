package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.dto.DnaSequence;
import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.repository.DnaSequenceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantStatService {

    private static Logger logger = LogManager.getLogger("MutantController");

    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    public DNAStat getAll() {
        List<DnaSequence> dnaSequences = dnaSequenceRepository.findAll();
        for(DnaSequence dnaSequence : dnaSequences){
            logger.info("MutantController.mutantIdentifier. Request body: " + dnaSequence.print());
        }
        return new DNAStat(10, 20, 0.5F);
    }

    public DNAStat getStats() {
        DNAStat dnaStat = dnaSequenceRepository.getStats();
        logger.info("MutantController.mutantIdentifier. Request body: " + dnaStat.print());
        return dnaStat;
    }

    public void update(DnaSequence dnaSequence){
        dnaSequenceRepository.update(dnaSequence);
    }
}
