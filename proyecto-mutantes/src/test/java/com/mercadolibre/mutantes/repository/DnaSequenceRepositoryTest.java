package com.mercadolibre.mutantes.repository;

import com.mercadolibre.mutantes.dto.DnaSequence;
import com.mercadolibre.mutantes.model.DNAStat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DnaSequenceRepositoryTest {

    @Autowired
    private DnaSequenceRepository dnaSequenceRepository;

    @Test
    public void getStatsTest(){
        DNAStat dnaStat = new DNAStat(2, 1, 0.6F);
        DNAStat dnaStatResponse = dnaSequenceRepository.getDnaStats();
        Assert.assertEquals(dnaStat.getCount_human_dna(), dnaStatResponse.getCount_human_dna());
        Assert.assertEquals(dnaStat.getCount_mutant_dna(), dnaStatResponse.getCount_mutant_dna());
        Assert.assertEquals(dnaStat.getRatio(), dnaStatResponse.getRatio(), 0.1F);
    }

    @Test
    public void updateTest(){
        List<DnaSequence> dnaSequencesBefore = dnaSequenceRepository.findAll();
        DnaSequence dnaSequence = new DnaSequence(10101010L, DnaSequence.DNA_TYPE_MUTANT, "AAAADDFFGGG");
        dnaSequenceRepository.update(dnaSequence);
        List<DnaSequence> dnaSequencesAfter = dnaSequenceRepository.findAll();
        
        Assert.assertEquals(dnaSequencesBefore.size()+1, dnaSequencesAfter.size());
    }
}
