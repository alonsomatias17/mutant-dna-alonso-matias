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
public class mutantIdentificationServiceTest {

    @Autowired
    private mutantIdentificationService mutantIdentificationService;

    @Test
    public void isMutantTest(){
        String[] dna = null;
        boolean response = mutantIdentificationService.isMutant(dna);
        Assert.assertTrue(response);
    }

    @Test(expected = InvalidDataException.class)
    public void isMutantBadInputException(){
        String[] dna = new String[mutantIdentificationService.DNA_SECUENCE_LENGHT-1];
        mutantIdentificationService.isMutant(dna);
    }
}
