package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MutantIdentificatiorServiceTest {

    @Autowired
    private MutantIdentificatiorService mutantIdentificatiorService;

    @Test
    public void isMutantTest(){
        String[] dna = this.sampleArrayMutant();
        boolean response = mutantIdentificatiorService.isMutant(dna);
        Assert.assertTrue(response);
    }

    @Test
    public void isNotMutantTest(){
        String[] dna = this.sampleArrayNotMutant();
        boolean response = mutantIdentificatiorService.isMutant(dna);
        Assert.assertFalse(response);
    }

    @Test(expected = InvalidDataException.class)
    public void isMutantInvalidSizeException(){
        String[] dna = this.sampleInvalidSizeArray();
        mutantIdentificatiorService.isMutant(dna);
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
