package com.mercadolibre.mutantes.dto;

public class DnaSequence {

    public static final String DNA_TYPE_MUTANT = "Mutant";
    public static final String DNA_TYPE_HUMAN = "Human";

    private Long id;
    private String type;
    private String dna;

    public DnaSequence(String type, String dna) {
        this.type=type;
        this.dna=dna;
    }

    public DnaSequence() {
    }

    public DnaSequence(Long i, String type, String dna) {
        this.id=i;
        this.type=type;
        this.dna=dna;
    }

    public String print(){
        return "Id: " + id.toString() + "Type: " + type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }
}
