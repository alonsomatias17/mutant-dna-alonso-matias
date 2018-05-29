package com.mercadolibre.mutantes.model.search;

import com.mercadolibre.mutantes.validator.MutantMatrixValidator;

public abstract class SearchMutantDNA {

    public boolean search(String[][] dnaMatrix, int r, int c) {
        int i=1;
        boolean containsMutantDNA = true;
        while (i< MutantMatrixValidator.DNA_SEQUENCE_LENGTH && containsMutantDNA){
            if(searchCondition(dnaMatrix, r, c, i))
                containsMutantDNA=false;
            i++;
        }
        return containsMutantDNA;
    }

    protected abstract boolean searchCondition(String[][] dnaMatrix, int r, int c, int i);

    public boolean canSearch(int size, int r, int c){
        return false;
    }
    public boolean canSearch(int size, int x){
        return false;
    }
}
