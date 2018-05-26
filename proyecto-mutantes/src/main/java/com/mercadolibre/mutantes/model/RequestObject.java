package com.mercadolibre.mutantes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RequestObject implements Serializable {
    public String[] dna = null;

    public String[] getDna() {
        return dna;
    }

    public List<String> print(){
        List<String> dnaString = new ArrayList<>();
        for(int i=0; i<dna.length; i++){
            dnaString.add(dna[i]);
        }
        return dnaString;
    }
}
