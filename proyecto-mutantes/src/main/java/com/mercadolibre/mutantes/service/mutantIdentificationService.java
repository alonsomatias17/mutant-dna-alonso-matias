package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantIdentificationService {

    private static Logger logger = LogManager.getLogger("ExceptionHandlingController");

    public static final int DNA_SEQUENCE_LENGTH = 4;
    public static final boolean IS_MUTANT = true;
    public static final boolean IS_NOT_MUTANT = false;

    @Autowired
    private MatrixTransformerService matrixTransformerService;

    public boolean isMutant(String[] dna){
        validateDnaMinLenght(dna.length);
        String[][] dnaMatrix = matrixTransformerService.arrayToMatrixConverter(dna);
        return isMutant(dnaMatrix, dna.length);
    }

    private void validateDnaMinLenght(int length) {
        if(length< DNA_SEQUENCE_LENGTH) {
            throw new InvalidDataException("Invalid Argument leght: "+ length + "Must be: " + DNA_SEQUENCE_LENGTH + "or higher");
        }
    }

    private boolean isMutant(String[][] dnaMatrix, int size) {
        int mutantDNASecuence = 0;
        System.out.println(size);
        int r = 0;
        int c = 0;
        while(r<size && mutantDNASecuence<2){
            logger.info("###New row. Number: "+r);
            while (c<size  && mutantDNASecuence<2){
                logger.info("New column. Number: "+c);
//                Ver si se puede sacar a una nueva clase
                if(canBeRight(size, c)){
                    if(searchRight(dnaMatrix, r, c)) {
                        mutantDNASecuence++;
                        logger.info("#########DNA match found!#########");
                    }
                }
                if(canBeDown(size, r)){
                    if(searchDown(dnaMatrix, r, c)) {
                        mutantDNASecuence++;
                        logger.info("#########DNA match found!#########");
                    }
                }
                if(canBeAtRDiagonal(size, r, c)){
                    if(searchRDiagonal(dnaMatrix, r, c)) {
                        mutantDNASecuence++;
                        logger.info("#########DNA match found!#########");
                    }
                }
                if(canBeAtLDiagonal(size, r, c)){
                    if(searchLDiagonal(dnaMatrix, r, c)) {
                        mutantDNASecuence++;
                        logger.info("#########DNA match found!#########");
                    }
                }
                c++;

            }
            c=0;
            r++;

        }
        if(mutantDNASecuence>1){
            logger.info("###DNA match! Mutant found, inform Mr. Magneto!!!###");
            return IS_MUTANT;
        }
        else{
            logger.info("###DNA not match. Just another human###");
            return IS_NOT_MUTANT;
        }
    }

    //TODO buscar forma de refactorizar
    private boolean canBeDown(int size, int r) {
        return size>(r+ DNA_SEQUENCE_LENGTH -1);
    }

    private boolean canBeAtRDiagonal(int size, int r, int c) {
        return this.canBeRight(size, r) && this.canBeDown(size, c);
    }

    private boolean canBeAtLDiagonal(int size, int r, int c) {
        return this.canBeLeft(r) && this.canBeDown(size, c);
    }

    private boolean canBeLeft(int r) {
        return r- DNA_SEQUENCE_LENGTH -1>=0;
    }

    private boolean canBeRight(int size, int c) {
        return size>(c+ DNA_SEQUENCE_LENGTH -1);
    }

    private boolean searchRight(String[][] dnaMatrix, int r, int c) {
        int i=1;
        boolean containsMutantDNA = true;
        logger.info("searchingRight");
        verifyData(dnaMatrix[r][c]);
        while (i< DNA_SEQUENCE_LENGTH && containsMutantDNA){
            verifyData(dnaMatrix[r][c+i]);
            if(!dnaMatrix[r][c].equals(dnaMatrix[r][c+i]))
                containsMutantDNA=false;
            i++;
        }
        return containsMutantDNA;
    }

    private boolean verifyData(String dna) {
        if(dna.matches("[ATGC]")) {
            return true;
        }
        else{
            new BadDataException("Incorrect character in dna string");
            return false;
        }
    }

    private boolean searchDown(String[][] dnaMatrix, int r, int c) {
        int i=1;
        boolean containsMutantDNA = true;
        logger.info("searchingDown");
        verifyData(dnaMatrix[r][c]);
        while (i< DNA_SEQUENCE_LENGTH && containsMutantDNA){
            verifyData(dnaMatrix[r][c]);
            if(!dnaMatrix[r][c].equals(dnaMatrix[r+i][c]))
                containsMutantDNA=false;
            i++;
        }
        return containsMutantDNA;
    }
    private boolean searchRDiagonal(String[][] dnaMatrix, int r, int c) {
        int i=1;
        boolean containsMutantDNA = true;
        logger.info("searchingRDiagonal");
        verifyData(dnaMatrix[r][c]);
        while (i< DNA_SEQUENCE_LENGTH && containsMutantDNA){
            verifyData(dnaMatrix[r][c]);
            if(!dnaMatrix[r][c].equals(dnaMatrix[r+i][c+i]))
                containsMutantDNA=false;
            i++;
        }
        return containsMutantDNA;
    }
    private boolean searchLDiagonal(String[][] dnaMatrix, int r, int c) {
        int i=1;
        boolean containsMutantDNA = true;
        logger.info("searchingLDiagonal");
        verifyData(dnaMatrix[r][c]);
        while (i< DNA_SEQUENCE_LENGTH && containsMutantDNA){
            verifyData(dnaMatrix[r][c]);
            if(!dnaMatrix[r][c].equals(dnaMatrix[r-i][c+i]))
                containsMutantDNA=false;
            i++;
        }
        return containsMutantDNA;
    }

}
