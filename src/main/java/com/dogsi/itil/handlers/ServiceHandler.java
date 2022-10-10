package com.dogsi.itil.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dogsi.itil.domain.ErrorCode;
import com.dogsi.itil.exceptions.ItemNotFoundException;

@ControllerAdvice
public class ServiceHandler {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse invalidRequestData(ItemNotFoundException ex) {
        return new ErrorResponse(ErrorCode.NOT_FOUND, ex.getMessage());
    }
}
