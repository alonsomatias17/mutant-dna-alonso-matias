package com.mercadolibre.mutantes.search;

import com.mercadolibre.mutantes.validator.MatrixFormatValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMutantDNALDiagonal extends SearchMutantDNA {
    private static Logger logger = LogManager.getLogger("SearchMutantDNALDiagonal");

    public SearchMutantDNALDiagonal(){
        super();
    }

    protected boolean searchCondition(String[][] dnaMatrix, int r, int c, int i) {
        logger.info("searchingLDiagonal");
        return !(dnaMatrix[r][c].equals(dnaMatrix[r+i][c-i]));
    }

    @Override
    public boolean canSearch(int size, int r, int c){
        return (c-(MatrixFormatValidator.DNA_SEQUENCE_LENGTH-1)>=0) &&
                (size>(r+ MatrixFormatValidator.DNA_SEQUENCE_LENGTH -1));
    }
}
