package com.mercadolibre.mutantes.search;

import com.mercadolibre.mutantes.validator.MatrixFormatValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMutantDNARDiagonal extends SearchMutantDNA {
    private static Logger logger = LogManager.getLogger("SearchMutantDNARDiagonal");

    public SearchMutantDNARDiagonal(){
        super();
    }

    protected boolean searchCondition(String[][] dnaMatrix, int r, int c, int i) {
        logger.info("searchingRDiagonal");
        return !(dnaMatrix[r][c].equals(dnaMatrix[r+i][c+i]));
    }

    @Override
    public boolean canSearch(int size, int r, int c){
        return (size>(c+ MatrixFormatValidator.DNA_SEQUENCE_LENGTH -1)) &&
                (size>(r+ MatrixFormatValidator.DNA_SEQUENCE_LENGTH -1));
    }
}
