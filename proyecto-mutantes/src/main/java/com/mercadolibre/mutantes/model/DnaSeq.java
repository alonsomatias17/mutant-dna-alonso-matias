package com.mercadolibre.mutantes.model;

import javax.persistence.*;

@Entity
@Table(name = "dna_sequence")
public class DnaSeq {

    public static final String DNA_TYPE_MUTANT = "Mutant";
    public static final String DNA_TYPE_HUMAN = "Human";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String dna;

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
