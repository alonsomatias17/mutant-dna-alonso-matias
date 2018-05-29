package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import com.mercadolibre.mutantes.service.MutantDnaService;
import com.mercadolibre.mutantes.service.MutantIdentificatiorService;
import com.mercadolibre.mutantes.validator.MutantMatrixValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
public class ExceptionHandlingControllerTest {

    @MockBean
    private MutantIdentificatiorService identificationService;

    @MockBean
    private MutantDnaService dnaService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void handleInvalidMatrixMinLengthTest() throws Exception {
        final String dnaDTOJson = sampleDnaDTOJson();
        String expected = "{\"code\":400,\"message\":\"Invalid Argument length. It must be: " + MutantMatrixValidator.DNA_SEQUENCE_LENGTH + "or higher, but it's: 3\",\"httpStatus\":\"BAD_REQUEST\"}";

        Mockito.when(identificationService.isMutant(Matchers.anyObject())).thenThrow(new InvalidDataException("Invalid Argument length. It must be: " + MutantMatrixValidator.DNA_SEQUENCE_LENGTH + "or higher, but it's: 3"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mutant")
                .accept(MediaType.APPLICATION_JSON).content(dnaDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void handleInvalidNotNxNMatrixTest() throws Exception {
        final String dnaDTOJson = sampleDnaDTOJson();
        String expected = "{\"code\":400,\"message\":\"Invalid Argument length. Rows and columns lengths must be equals. Row: 3. Columns 4\",\"httpStatus\":\"BAD_REQUEST\"}";

        Mockito.when(identificationService.isMutant(Matchers.anyObject()))
                .thenThrow(new InvalidDataException("Invalid Argument length. Rows and columns lengths must be equals. Row: 3. Columns 4"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mutant")
                .accept(MediaType.APPLICATION_JSON).content(dnaDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void handleInvalidUnequalsRowsAndColumnsLengthsTest() throws Exception {
        final String dnaDTOJson = sampleDnaDTOJson();
        String expected = "{\"code\":400,\"message\":\"Invalid Argument length. Rows has unequals columns lengths. Row 0 size: 4. Row 2 size: 5\",\"httpStatus\":\"BAD_REQUEST\"}";

        Mockito.when(identificationService.isMutant(Matchers.anyObject())).thenThrow(new InvalidDataException("Invalid Argument length. Rows has unequals columns lengths. Row 0 size: 4. Row 2 size: 5"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mutant")
                .accept(MediaType.APPLICATION_JSON).content(dnaDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void handleBadDataInMatrixTest() throws Exception {
        final String dnaDTOJson = sampleDnaDTOJson();
        String expected = "{\"code\":400,\"message\":\"Incorrect character in dna string. Character: K\",\"httpStatus\":\"BAD_REQUEST\"}";

        Mockito.when(identificationService.isMutant(Matchers.anyObject())).thenThrow(new BadDataException("Incorrect character in dna string. Character: K"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mutant")
                .accept(MediaType.APPLICATION_JSON).content(dnaDTOJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }


    private String sampleDnaDTOJson() {
        return "{" +
                "\"dna\": [\"ATG\",\"CAG\",\"TTA\"]" +
                "}";
    }
}
