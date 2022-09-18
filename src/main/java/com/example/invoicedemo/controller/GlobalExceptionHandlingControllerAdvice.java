package com.example.invoicedemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {


//    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    // 409
    @ExceptionHandler({DataIntegrityViolationException.class ,SQLException.class, DataAccessException.class})
    public String conflict(Exception exception , Model model) {

        log.error("Request raised " + exception.getClass().getSimpleName());

        String msg = ((DataIntegrityViolationException) exception).getMostSpecificCause().getMessage();
        model.addAttribute("exception",exception.getClass().getSimpleName());
        model.addAttribute("message",((DataIntegrityViolationException) exception).getMostSpecificCause().getMessage());
        return "error/databaseError";
    }

}
