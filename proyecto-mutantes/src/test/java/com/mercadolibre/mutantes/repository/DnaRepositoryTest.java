package com.mercadolibre.mutantes.repository;

import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.model.DnaSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DnaRepositoryTest {

    @Autowired
    private DnaRepository dnaRepository;

    @Test
    public void addADnaSequence() {
        dnaRepository.addADnaSequence(DnaSequence.DNA_TYPE_MUTANT, "AAAAAAAAAAAAA");
    }

    @Test
    public void getDnaStats() {
        DNAStat dnaStat = dnaRepository.getDnaStats();
        Assert.assertNotNull(dnaStat);
    }
}
