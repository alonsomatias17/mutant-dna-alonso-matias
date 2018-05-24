package com.mercadolibre.mutantes.search;

import com.mercadolibre.mutantes.service.MutantIdentificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchMutantDNARDiagonal extends SearchMutantDNA {
    private static Logger logger = LogManager.getLogger("SearchMutantDNARDiagonal");

    public SearchMutantDNARDiagonal(){
        super();
    }

    protected boolean searchCondition(String[][] dnaMatrix, int r, int c, int i) {
        logger.info("searchingRDiagonal");
        super.verifyData(dnaMatrix[r+i][c+i]);
        return !(dnaMatrix[r][c].equals(dnaMatrix[r+i][c+i]));
    }

    @Override
    public boolean canSearch(int size, int r, int c){
        return (size>(c+ MutantIdentificationService.DNA_SEQUENCE_LENGTH -1)) &&
                (size>(r+ MutantIdentificationService.DNA_SEQUENCE_LENGTH -1));
    }
}
