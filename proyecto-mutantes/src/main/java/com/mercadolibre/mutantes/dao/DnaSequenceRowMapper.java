package com.mercadolibre.mutantes.dao;

import com.mercadolibre.mutantes.model.DnaSequence;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DnaSequenceRowMapper implements RowMapper<DnaSequence> {
        @Override
        public DnaSequence mapRow(ResultSet rs, int rowNum) throws SQLException {
            DnaSequence dnaSequence = new DnaSequence();
            dnaSequence.setId(rs.getLong("id"));
            dnaSequence.setType(rs.getString("type"));
            dnaSequence.setDna(rs.getString("dna"));
            return dnaSequence;
        }
}
