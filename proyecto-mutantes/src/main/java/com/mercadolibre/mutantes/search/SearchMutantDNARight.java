package com.mercadolibre.mutantes.search;

import com.mercadolibre.mutantes.service.MutantIdentificationService;
import com.mercadolibre.mutantes.validator.MatrixFormatValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMutantDNARight extends SearchMutantDNA {
    private static Logger logger = LogManager.getLogger("SearchMutantDNARight");


    public SearchMutantDNARight(){
        super();
    }

    protected boolean searchCondition(String[][] dnaMatrix, int r, int c, int i) {
        logger.info("searchingRight");
        MatrixFormatValidator.verifyData(dnaMatrix[r][c+i]);
        return !(dnaMatrix[r][c].equals(dnaMatrix[r][c+i]));
    }

    @Override
    public boolean canSearch(int size, int x){
        return size>(x+ MutantIdentificationService.DNA_SEQUENCE_LENGTH -1);
    }
}
