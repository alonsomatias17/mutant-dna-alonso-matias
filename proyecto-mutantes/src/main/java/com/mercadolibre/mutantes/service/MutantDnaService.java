package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.model.DNAStat;

public interface MutantDnaService {

    void addADnaSequence(String type, String dna);
    DNAStat getDnaStats();
}
