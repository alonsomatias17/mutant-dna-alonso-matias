package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.exception.InvalidDataException;
import com.mercadolibre.mutantes.validator.MutantMatrixValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MutantIdentificationServiceTest {

    @Autowired
    private MutantIdentificationService mutantIdentificationService;

    @Test
    public void isMutantTest(){
        String[] dna = this.sampleArrayMutant();
        boolean response = mutantIdentificationService.isMutant(dna);
        Assert.assertTrue(response);
    }

    @Test
    public void isNotMutantTest(){
        String[] dna = this.sampleArrayNotMutant();
        boolean response = mutantIdentificationService.isMutant(dna);
        Assert.assertFalse(response);
    }

    @Test(expected = InvalidDataException.class)
    public void isMutantInvalidSizeException(){
        String[] dna = this.sampleInvalidSizeArray();
        mutantIdentificationService.isMutant(dna);
    }

    private String[] sampleArrayMutant() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        return dna;
    }

    private String[] sampleArrayNotMutant() {
        String[] dna = {"TTGCGA","CAGTAC","TTATGT","AGAAGG","CCTCTA","TCACTG"};
        return dna;
    }

    private String[] sampleInvalidSizeArray() {
        String[] dna = {"TTG","CAG","TTA"};
        return dna;
    }
}
