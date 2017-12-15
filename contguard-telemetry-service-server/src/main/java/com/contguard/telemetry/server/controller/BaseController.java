package com.contguard.telemetry.server.controller;

import com.contguard.telemetry.server.exception.BaseException;
import com.contguard.telemetry.server.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@Controller
@ControllerAdvice
public class BaseController {
    private final Logger _logger = LoggerFactory.getLogger(getClass());


    public BaseController() {
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        BaseException baseException = BaseException.parseException(e);
        _logger.error("Exception caught on base controller", baseException);
        return new ResponseEntity(baseException.getErrorResponse(), baseException.getHttpStatusCode());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("ACCESS_DENIED", e.getMessage());
        _logger.error("Exception caught on base controller", e);
        return new ResponseEntity(errorResponse, HttpStatus.FORBIDDEN);
    }
}
