package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.model.search.*;
import com.mercadolibre.mutantes.validator.MutantMatrixValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantIdentificationService {

    private static Logger logger = LogManager.getLogger("ExceptionHandlingController");

    public static final int DNA_SEQUENCE_FOUND = 1;
    public static final int DNA_SEQUENCE_NOT_FOUND = 0;
    public static final boolean IS_MUTANT = true;
    public static final boolean IS_NOT_MUTANT = false;

    @Autowired
    private MatrixTransformerService matrixTransformerService;

    public boolean isMutant(String[] dna){
        MutantMatrixValidator.validateDnaMatrixLength(dna);
        String[][] dnaMatrix = matrixTransformerService.arrayToMatrixConverter(dna);
        return isMutant(dnaMatrix, dna.length);
    }

    private boolean isMutant(String[][] dnaMatrix, int size) {
        int mutantDNASequence = 0;
        int r = 0;
        int c = 0;
        while(r<size && mutantDNASequence < MutantMatrixValidator.DNA_SEQUENCES_NEEDED_AMOUNT){
            logger.debug("New row: "+r);
            while (c<size  && mutantDNASequence < MutantMatrixValidator.DNA_SEQUENCES_NEEDED_AMOUNT){
                logger.debug("New column: "+c);
                mutantDNASequence += getMutantDNASequence(dnaMatrix, size, r, c);
                c++;
            }
            c=0;
            r++;
        }
        return isMutant(mutantDNASequence);
    }

    private boolean isMutant(int mutantDNASequence) {
        if(mutantDNASequence >= MutantMatrixValidator.DNA_SEQUENCES_NEEDED_AMOUNT){
            logger.info("DNA match! Mutant found, inform Mr. Magneto!!!");
            return IS_MUTANT;
        }
        else{
            logger.info("DNA not match. Just another human");
            return IS_NOT_MUTANT;
        }
    }

    private int getMutantDNASequence(String[][] dnaMatrix, int size, int r, int c) {
        int mutantDNASequence = 0;
        SearchMutantDNA searchMutantDNA;

        searchMutantDNA = new SearchMutantDNARight();
        if(searchMutantDNA.canSearch(size, c)){
            mutantDNASequence += searchForMutantDNASequence(dnaMatrix, r, c, new SearchMutantDNARight());
        }
        searchMutantDNA = new SearchMutantDNADown();
        if(searchMutantDNA.canSearch(size, r)){
            mutantDNASequence += searchForMutantDNASequence(dnaMatrix, r, c, new SearchMutantDNADown());
        }
        searchMutantDNA = new SearchMutantDNARDiagonal();
        if(searchMutantDNA.canSearch(size, r, c)){
            mutantDNASequence += searchForMutantDNASequence(dnaMatrix, r, c, new SearchMutantDNARDiagonal());
        }
        searchMutantDNA = new SearchMutantDNALDiagonal();
        if(searchMutantDNA.canSearch(size, r, c)){
            mutantDNASequence += searchForMutantDNASequence(dnaMatrix, r, c, new SearchMutantDNALDiagonal());
        }
        return mutantDNASequence;
    }

    private int searchForMutantDNASequence(String[][] dnaMatrix, int r, int c, SearchMutantDNA searchMutantDNA) {
    if(searchMutantDNA.search(dnaMatrix, r, c)){
        logger.info("DNA match. Mutant dna sequence found!");
        return DNA_SEQUENCE_FOUND;
    }
    return DNA_SEQUENCE_NOT_FOUND;
    }
}
