package com.mercadolibre.mutantes.model.search;

import com.mercadolibre.mutantes.validator.MutantMatrixValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMutantDNALDiagonal extends SearchMutantDNA {
    private static Logger logger = LogManager.getLogger("SearchMutantDNALDiagonal");

    public SearchMutantDNALDiagonal(){
        super();
    }

    protected boolean searchCondition(String[][] dnaMatrix, int r, int c, int i) {
        return !(dnaMatrix[r][c].equals(dnaMatrix[r+i][c-i]));
    }

    @Override
    public boolean canSearch(int size, int r, int c){
        return (c-(MutantMatrixValidator.DNA_SEQUENCE_LENGTH-1)>=0) &&
                (size>(r+ MutantMatrixValidator.DNA_SEQUENCE_LENGTH -1));
    }
}
