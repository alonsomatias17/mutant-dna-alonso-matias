package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.dto.DnaSequence;
import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.model.RequestObject;
import com.mercadolibre.mutantes.service.MutantIdentificationService;
import com.mercadolibre.mutantes.service.MutantStatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
public class MutantController {

    private static Logger logger = LogManager.getLogger("MutantController");

    @Autowired
    private MutantStatService mutantStatService;

    @Autowired
    private MutantIdentificationService mutantIdentificationService;

    @RequestMapping(value = "/mutant",  method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> mutantIdentifier(@RequestBody RequestObject requestObject) {

        logger.info("MutantController.mutantIdentifier. Request body: " + requestObject.print());
        if (mutantIdentificationService.isMutant( requestObject.getDna())) {
            mutantStatService.update(new DnaSequence(10L, DnaSequence.DNA_TYPE_MUTANT, Arrays.toString(requestObject.getDna()) ));
            return ResponseEntity.status(HttpStatus.OK).build();

        }
        mutantStatService.update(new DnaSequence(10L,DnaSequence.DNA_TYPE_HUMAN, Arrays.toString(requestObject.getDna()) ));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public DNAStat getDNAStats(){
        logger.info("MutantController.getDNAStats. Getting stats");
        DNAStat dnaStat = mutantStatService.getStats();
        logger.info("Stats: " + dnaStat.toString());
        return dnaStat;
    }
}
