package com.mercadolibre.mutantes.search;

import com.mercadolibre.mutantes.validator.MatrixFormatValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMutantDNADown  extends SearchMutantDNA {
    private static Logger logger = LogManager.getLogger("SearchMutantDNADown");

    public SearchMutantDNADown(){
        super();
    }

    protected boolean searchCondition(String[][] dnaMatrix, int r, int c, int i) {
        logger.info("searchingDown");
        return !(dnaMatrix[r][c].equals(dnaMatrix[r+i][c]));
    }

    @Override
    public boolean canSearch(int size, int x){
        return size>(x+ MatrixFormatValidator.DNA_SEQUENCE_LENGTH -1);
    }
}
