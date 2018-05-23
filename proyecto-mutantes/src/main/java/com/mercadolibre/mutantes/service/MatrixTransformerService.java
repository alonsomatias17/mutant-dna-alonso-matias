package com.mercadolibre.mutantes.service;

import org.springframework.stereotype.Service;

@Service
public class MatrixTransformerService {

    public String[][] arrayToMatrixConverter(String[] dna) {
        String[][] dnaMatrix  = {{"A","T","G","C","G","A"},
                {"C","A","G","T","G","C"},
                {"T","T","A","T","G","T"},
                {"A","G","A","A","G","G"},
                {"C","C","C","C","T","A"},
                {"T","C","A","C","T","G"}};
        return dnaMatrix;
    }

    public String[][] arrayToMatrixConverter2(String[] dna) {
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
