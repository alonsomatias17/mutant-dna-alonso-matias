package com.mercadolibre.mutantes.repository;

import com.mercadolibre.mutantes.dao.DnaStatRowMapper;
import com.mercadolibre.mutantes.model.DNAStat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DnaRepository {

    private static Logger logger = LogManager.getLogger("DnaRepository");


    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addADnaSequence(String type, String dna){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO dna_sequence(type, dna)VALUES(?,?)", type, dna);
        logger.info("Record successfully saved in DataBase");
    }

    public DNAStat getDnaStats() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT (SELECT COUNT(ID) from DNA_SEQUENCE WHERE TYPE='Mutant') as mutant, " +
                "(SELECT COUNT(ID) from  DNA_SEQUENCE WHERE TYPE='Human') as human";
        List<DNAStat> dnaStats = jdbcTemplate.query(sql, new DnaStatRowMapper());
        logger.info("Get stat from DataBase. Query: "+sql);
        return dnaStats.get(0);
    }
}
