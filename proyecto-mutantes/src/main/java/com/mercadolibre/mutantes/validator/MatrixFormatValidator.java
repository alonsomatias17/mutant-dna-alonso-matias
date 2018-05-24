package com.mercadolibre.mutantes.validator;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class MatrixFormatValidator {

    private static String validData;
    public static final int DNA_SEQUENCE_LENGTH = 4;

    private  MatrixFormatValidator(){
    }

    public static boolean verifyData(String dna) {
        if(dna.matches(validData)) {
            return true;
        }
        else{
            new BadDataException("Incorrect character in dna string");
            return false;
        }
    }

    public static void validateDnaMinLength(int length) {
        if(length< MatrixFormatValidator.DNA_SEQUENCE_LENGTH) {
            throw new InvalidDataException("Invalid Argument leght: "+ length + "Must be: " + MatrixFormatValidator.DNA_SEQUENCE_LENGTH + "or higher");
        }
    }
    @Value("${dna.validData}")
    public void setValidData(String validData) {
        MatrixFormatValidator.validData = validData;
    }

}
