package com.mercadolibre.mutantes.validator;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import com.mercadolibre.mutantes.service.MutantIdentificationService;

public final class MatrixFormatValidator {

    public static boolean verifyData(String dna) {
        if(dna.matches("[ATGC]")) {
            return true;
        }
        else{
            new BadDataException("Incorrect character in dna string");
            return false;
        }
    }

    public static void validateDnaMinLength(int length) {
        if(length< MutantIdentificationService.DNA_SEQUENCE_LENGTH) {
            throw new InvalidDataException("Invalid Argument leght: "+ length + "Must be: " + MutantIdentificationService.DNA_SEQUENCE_LENGTH + "or higher");
        }
    }
}
