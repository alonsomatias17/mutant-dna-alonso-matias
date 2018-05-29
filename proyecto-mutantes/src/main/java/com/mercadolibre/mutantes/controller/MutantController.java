package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.model.DnaSequence;
import com.mercadolibre.mutantes.model.RequestObject;
import com.mercadolibre.mutantes.service.MutantDnaService;
import com.mercadolibre.mutantes.service.MutantIdentificatiorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@Api(value="Stats", description="Operations pertaining to market data jobs")
public class MutantController {

    private static Logger logger = LogManager.getLogger("MutantController");

    @Autowired
    private MutantIdentificatiorService mutantIdentificatiorService;

    @Autowired
    private MutantDnaService dnaService;

    @RequestMapping(value = "/mutant",  method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Search for mutant dna in a certain sequence sequence, then save the result")
    public ResponseEntity<String> mutantIdentifier(@RequestBody RequestObject requestObject) {
        logger.info("MutantController.mutantIdentifier. Request body: " + requestObject.print());
        if (mutantIdentificatiorService.isMutant( requestObject.getDna())) {
            dnaService.addADnaSequence(DnaSequence.DNA_TYPE_MUTANT, Arrays.toString(requestObject.getDna()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        dnaService.addADnaSequence(DnaSequence.DNA_TYPE_HUMAN, Arrays.toString(requestObject.getDna()));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @ApiOperation(value = "Shows stats for mutnt and human dna")
    public DNAStat getDnaStats(){
        logger.info("MutantController.getDnaStats. Getting stats");
        return dnaService.getDnaStats();
    }
}
