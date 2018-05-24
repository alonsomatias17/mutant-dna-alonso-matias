package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.search.*;
import com.mercadolibre.mutantes.validator.MatrixFormatValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantIdentificationService {

    private static Logger logger = LogManager.getLogger("ExceptionHandlingController");

    //TODO mover a archivo de propiedades
    public static final int DNA_SEQUENCE_LENGTH = 4;
    public static final int DNA_SEQUENCE_FOUND = 1;
    public static final int DNA_SEQUENCE_NOT_FOUND = 0;
    public static final boolean IS_MUTANT = true;
    public static final boolean IS_NOT_MUTANT = false;

    @Autowired
    private MatrixTransformerService matrixTransformerService;

    public boolean isMutant(String[] dna){
        MatrixFormatValidator.validateDnaMinLength(dna.length);
        String[][] dnaMatrix = matrixTransformerService.arrayToMatrixConverter(dna);
        return isMutant(dnaMatrix, dna.length);
    }

    private boolean isMutant(String[][] dnaMatrix, int size) {
        int mutantDNASequence = 0;
        System.out.println(size);
        int r = 0;
        int c = 0;
        while(r<size && mutantDNASequence<2){
            logger.info("###New row. Number: "+r);
            while (c<size  && mutantDNASequence<2){
                logger.info("New column. Number: "+c);
                mutantDNASequence += getMutantDNASequence(dnaMatrix, size, r, c);
                c++;
            }
            c=0;
            r++;
        }
        if(mutantDNASequence>1){
            logger.info("###DNA match! Mutant found, inform Mr. Magneto!!!###");
            return IS_MUTANT;
        }
        else{
            logger.info("###DNA not match. Just another human###");
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
        logger.info("#########DNA match found!#########");
        return DNA_SEQUENCE_FOUND;
    }
    return DNA_SEQUENCE_NOT_FOUND;
    }
}
