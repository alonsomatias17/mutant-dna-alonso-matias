package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.service.MutantIdentificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
    private MutantIdentificationService mutantIdentificationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mutantIdentifierTest() throws Exception {
        final String dnaDTOJson = sampleDnaDTOJson();
        Mockito.when(mutantIdentificationService.isMutant(Mockito.any(String[].class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mutant")
                .accept(MediaType.APPLICATION_JSON).content(dnaDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private String sampleDnaDTOJson() {
        return "{ \"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}";
    }
}
