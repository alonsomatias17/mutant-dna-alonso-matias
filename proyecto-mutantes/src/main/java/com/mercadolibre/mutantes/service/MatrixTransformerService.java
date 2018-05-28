package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.validator.MutantMatrixValidator;
import org.springframework.stereotype.Service;

@Service
public class MatrixTransformerService {

    public String[][] arrayToMatrixConverter(String[] dna) {
        int size = dna.length;
        String[][] dnaMatrix  = new String[size][size];

        for(int i=0; i<size; i++){
            String[] aux = dna[i].split("");
            for (int j=0; j<size; j++){
                MutantMatrixValidator.verifyData(aux[j]);
                dnaMatrix[i][j]=aux[j];
            }
        }
        return dnaMatrix;
    }
}
