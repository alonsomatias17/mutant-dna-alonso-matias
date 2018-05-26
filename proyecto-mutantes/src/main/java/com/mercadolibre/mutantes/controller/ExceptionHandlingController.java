package com.mercadolibre.mutantes.controller;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import com.mercadolibre.mutantes.model.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by I689570 on 11/21/2017.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    private static Logger logger = LogManager.getLogger("ExceptionHandlingController");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ErrorResponse eResponse = new ErrorResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(eResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<ErrorResponse> handleBadData(BadDataException ex) {
        logger.error(ex.getMessage(), ex);
        ErrorResponse eResponse = new ErrorResponse(400, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(eResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidData(InvalidDataException ex) {
        logger.error(ex.getMessage(), ex);
        ErrorResponse eResponse = new ErrorResponse(400, HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(eResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}


