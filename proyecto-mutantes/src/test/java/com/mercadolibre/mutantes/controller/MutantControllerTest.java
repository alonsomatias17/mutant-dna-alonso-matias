package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.model.DNAStat;
import com.mercadolibre.mutantes.service.MutantDnaService;
import com.mercadolibre.mutantes.service.MutantIdentificatiorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MutantController.class, secure = false)
public class MutantControllerTest {

    @MockBean
    private MutantIdentificatiorService mutantIdentificatiorService;

    @MockBean
    private MutantDnaService dnaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mutantIdentifierTest() throws Exception {
        final String dnaDTOJson = sampleDnaDTOJson();
        Mockito.when(mutantIdentificatiorService.isMutant(Mockito.any(String[].class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mutant")
                .accept(MediaType.APPLICATION_JSON).content(dnaDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getJobsRecordsTest() throws Exception {
        DNAStat dnaStat = getDNAStats();
        String expected = getDNAStatsExpectedResponse();

        Mockito.when(dnaService.getDnaStats()).thenReturn(dnaStat);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/stats")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    private DNAStat getDNAStats() {
        return new DNAStat(10, 20, 0.5F);
    }

    private String getDNAStatsExpectedResponse() {
        return "{" +
                "    \"count_mutant_dna\": 10," +
                "    \"count_human_dna\": 20," +
                "    \"ratio\": 0.5" +
                "}";
    }

    private String sampleDnaDTOJson() {
        return "{" +
                "\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]" +
                "}";
    }
}
