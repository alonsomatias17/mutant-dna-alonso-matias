package com.mercadolibre.mutantes.service;

import org.springframework.stereotype.Service;

@Service
public class MatrixTransformerService {

    public String[][] arrayToMatrixConverter(String[] dna) {
        int size = dna.length;
        String[][] dnaMatrix  = new String[size][size];

        for(int i=0; i<size; i++){
            String[] aux = dna[i].split("");
            for (int j=0; j<size; j++){
                dnaMatrix[i][j]=aux[j];
            }
        }
        return dnaMatrix;
    }
}
