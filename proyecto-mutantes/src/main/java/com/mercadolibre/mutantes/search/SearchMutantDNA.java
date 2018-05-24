package com.mercadolibre.mutantes.search;

import com.mercadolibre.mutantes.exception.BadDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mercadolibre.mutantes.service.MutantIdentificationService.DNA_SEQUENCE_LENGTH;

public abstract class SearchMutantDNA {

    public boolean search(String[][] dnaMatrix, int r, int c) {
        int i=1;
        boolean containsMutantDNA = true;
        verifyData(dnaMatrix[r][c]);
        while (i< DNA_SEQUENCE_LENGTH && containsMutantDNA){

            if(searchCondition(dnaMatrix, r, c, i))
                containsMutantDNA=false;
            i++;
        }
        return containsMutantDNA;
    }

    protected abstract boolean searchCondition(String[][] dnaMatrix, int r, int c, int i);

    //TODO mover
    public boolean verifyData(String dna) {
        if(dna.matches("[ATGC]")) {
            return true;
        }
        else{
            new BadDataException("Incorrect character in dna string");
            return false;
        }
    }

    public boolean canSearch(int size, int r, int c){
        return false;
    }
    public boolean canSearch(int size, int x){
        return false;
    }
}
