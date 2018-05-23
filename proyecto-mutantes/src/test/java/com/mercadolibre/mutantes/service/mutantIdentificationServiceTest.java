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
public class MutantIdentificationServiceTest {

    @Autowired
    private MutantIdentificationService MutantIdentificationService;

    @Test
    public void isMutantTest(){
        String[] dna = new String[6];
        boolean response = MutantIdentificationService.isMutant(dna);
        Assert.assertTrue(response);
    }

    @Test(expected = InvalidDataException.class)
    public void isMutantBadInputException(){
        String[] dna = new String[MutantIdentificationService.DNA_SEQUENCE_LENGTH -1];
        MutantIdentificationService.isMutant(dna);
    }
}
