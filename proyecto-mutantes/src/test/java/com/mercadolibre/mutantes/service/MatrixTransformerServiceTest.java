package com.mercadolibre.mutantes.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MatrixTransformerServiceTest {

    @Autowired
    private MatrixTransformerService matrixTransformerService;

    @Test
    public void arrayToMatrixConverter2Test(){
        String[] dna = this.sampleArray();
        String[][] resultMatrix = matrixTransformerService.arrayToMatrixConverter(dna);
        String[][] expectedMatrix = this.sampleMatrix();
        Assert.assertTrue(Arrays.deepEquals(resultMatrix, expectedMatrix));
    }

    private String[][] sampleMatrix() {
        String[][] dnaMatrix  = {{"A","T","G","C","G","A"},
                                {"C","A","G","T","G","C"},
                                {"T","T","A","T","G","T"},
                                {"A","G","A","A","G","G"},
                                {"C","C","C","C","T","A"},
                                {"T","C","A","C","T","G"}};
        return dnaMatrix;
    }

    private String[] sampleArray() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        return dna;
    }
}
