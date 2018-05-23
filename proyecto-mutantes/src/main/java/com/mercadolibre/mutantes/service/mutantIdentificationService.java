package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.exception.InvalidDataException;
import org.springframework.stereotype.Service;

@Service
public class mutantIdentificationService {

    public static final int DNA_SECUENCE_LENGHT = 4;

    public boolean isMutant(String[] dna){
        validateDnaMinLenght(dna.length);
        String[][] dnaMatrix = arrayToMatrixConverter(dna);
        return isMutant(dnaMatrix, dna.length);
    }

    private void validateDnaMinLenght(int length) {
        if(length<DNA_SECUENCE_LENGHT) {
            throw new InvalidDataException("Invalid Argument leght: "+ length + "Must be: " + DNA_SECUENCE_LENGHT + "or higher");
        }
    }

    private boolean isMutant(String[][] dnaMatrix, int size) {
        boolean containsMutantDNA = false;
        System.out.println(size);
        int r = 0;
        int c = 0;
        while(r<size && containsMutantDNA==false){
            while (c<size){
//                Ver si se puede sacar a una nueva clase
                if(canBeRight(size, c)){
                    serchRight(dnaMatrix, r, c);
                }
                if(canBeDown(size, r)){
                    serchDown(dnaMatrix, r, c);
                }
                if(canBeAtRDiagonal(size, r, c)){
                    serchRDiagonal(dnaMatrix, r, c);
                }
                if(canBeAtLDiagonal(size, r, c)){
                    serchLDiagonal(dnaMatrix, r, c);
                }
            }
            r++;
        }
        return  containsMutantDNA;
    }

    private boolean canBeDown(int size, int r) {
        return (r+DNA_SECUENCE_LENGHT-1)>size;
    }

    private boolean canBeAtRDiagonal(int size, int r, int c) {
        return this.canBeRight(size, r) && this.canBeDown(size, c);
    }

    private boolean canBeAtLDiagonal(int size, int r, int c) {
        return this.canBeLeft(r) && this.canBeDown(size, c);
    }

    private boolean canBeLeft(int r) {
        return r-DNA_SECUENCE_LENGHT-1>=0;
    }

    private boolean canBeRight(int size, int c) {
        return (c+DNA_SECUENCE_LENGHT-1)>size;
    }

    private void serchRight(String[][] dnaMatrix, int r, int c) {
    }
    private void serchDown(String[][] dnaMatrix, int r, int c) {
    }
    private void serchRDiagonal(String[][] dnaMatrix, int r, int c) {
    }
    private void serchLDiagonal(String[][] dnaMatrix, int r, int c) {
    }

    private String[][] arrayToMatrixConverter(String[] dna) {
        String[][] dnaMatrix  = {{"A","T","G","C","G","A"},
                                {"C","A","G","T","G","C"},
                                {"T","T","A","T","G","T"},
                                {"A","G","A","A","G","G"},
                                {"C","C","C","C","T","A"},
                                {"T","C","A","C","T","G"}};
        return dnaMatrix;
    }
}
