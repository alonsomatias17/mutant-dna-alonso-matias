package com.mercadolibre.mutantes.service;

import com.mercadolibre.mutantes.model.DNAStat;
import org.springframework.stereotype.Service;

@Service
public class MutantStatService {

    public DNAStat getStats() {
        return new DNAStat(10, 20, 0.5F);
    }
}
