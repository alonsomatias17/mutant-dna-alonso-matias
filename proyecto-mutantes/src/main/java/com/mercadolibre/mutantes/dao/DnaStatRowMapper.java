package com.mercadolibre.mutantes.dao;

import com.mercadolibre.mutantes.model.DNAStat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DnaStatRowMapper implements RowMapper<DNAStat> {

    @Override
    public DNAStat mapRow(ResultSet rs, int rowNum) throws SQLException {
        DNAStat dnaStat = new DNAStat();
        dnaStat.setCount_mutant_dna(rs.getInt("mutant"));
        dnaStat.setCount_human_dna(rs.getInt("human"));
        dnaStat.setRatio((float)dnaStat.getCount_mutant_dna()/(dnaStat.getCount_human_dna()+dnaStat.getCount_mutant_dna()));
        return dnaStat;
    }
}
