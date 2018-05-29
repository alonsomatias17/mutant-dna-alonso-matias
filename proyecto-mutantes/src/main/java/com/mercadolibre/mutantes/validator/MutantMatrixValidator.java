package com.mercadolibre.mutantes.validator;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class MutantMatrixValidator {

    private static String validData;
    public static final int DNA_SEQUENCE_LENGTH = 4;
    public static final int DNA_SEQUENCES_NEEDED_AMOUNT = 2;

    private MutantMatrixValidator(){
    }

    public static boolean verifyData(String dna) {
        if(dna.matches(validData)) {
            return true;
        }
        else{
            throw new BadDataException("Incorrect character in dna string. Character: " +dna);
        }
    }

    public static void validateDnaMatrixLength(String[] matrix) {
        validateRowsEqualsLength(matrix);
        validateNxNMatrix(matrix.length, matrix[0].length());
        validateMatrixMinLength(matrix.length);
    }

    private static void validateMatrixMinLength(int length) {
        if(length< MutantMatrixValidator.DNA_SEQUENCE_LENGTH) {
            throw new InvalidDataException("Invalid Argument length. It must be: " + MutantMatrixValidator.DNA_SEQUENCE_LENGTH + "or higher, but it's: "+ length );
        }
    }

    private static void validateNxNMatrix(int rows, int columns) {
        if(rows!=columns){
            throw new InvalidDataException("Invalid Argument length. Rows and columns lengths must be equals. Row: " +rows+ ". Columns " +columns);
        }
    }

    private static void validateRowsEqualsLength(String[] matrix) {
        int i = 1;
        while(i<matrix.length) {
            if(!(matrix[0].length() == matrix[i].length()) ){
                throw new InvalidDataException("Invalid Argument length. Rows has unequals columns lengths. Row 0 size: " +matrix[0].length()+ ". Row " +i+ " size: " + matrix[i].length());
            }
            i++;
        }
    }

    @Value("${dna.validData}")
    public void setValidData(String validData) {
        MutantMatrixValidator.validData = validData;
    }
}
