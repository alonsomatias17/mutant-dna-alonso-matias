package com.mercadolibre.mutantes.model;

public class DNAStat {
    private int count_mutant_dna;
    private int count_human_dna;
    private float ratio;

    public DNAStat (){
    }

    public DNAStat(int count_mutant_dna, int count_human_dna, float ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }
}
