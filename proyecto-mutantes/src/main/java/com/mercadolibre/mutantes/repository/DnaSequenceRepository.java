package com.mercadolibre.mutantes.repository;

import com.mercadolibre.mutantes.dao.DnaSequenceRowMapper;
import com.mercadolibre.mutantes.dao.DnaStatRowMapper;
import com.mercadolibre.mutantes.dto.DnaSequence;
import com.mercadolibre.mutantes.model.DNAStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DnaSequenceRepository {

    @Autowired
    private JdbcTemplate jtm;

    public List<DnaSequence> findAll() {
        String sql = "SELECT * FROM DNA_SEQUENCE";
        List<DnaSequence> dnaSequences = jtm.query(sql, new DnaSequenceRowMapper());
        return dnaSequences;
    }

    public DNAStat getStats() {
        String sql = "SELECT (SELECT COUNT(ID) from DNA_SEQUENCE WHERE TYPE='Mutant') as mutant, " +
                            "(SELECT COUNT(ID) from  DNA_SEQUENCE WHERE TYPE='Human') as human";
        List<DNAStat> dnaStats = jtm.query(sql, new DnaStatRowMapper());
        return dnaStats.get(0);
    }

    public void update(DnaSequence dnaSequence) {
        String sql = "insert into DNA_SEQUENCE values("+ dnaSequence.getId()+",'"+ dnaSequence.getType()+"','"+ dnaSequence.getDna()+"')";
        jtm.update(sql);
    }
}
