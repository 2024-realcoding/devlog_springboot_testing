package com.cnu.devlog_springboot.error;

import com.cnu.devlog_springboot.service.SlackAlertService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@AllArgsConstructor
@RestControllerAdvice
public class RealCodingExceptionHandler {
    private final SlackAlertService slackService;
    @ExceptionHandler(SlangBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleSlangRequestException(SlangBadRequestException exception,  HttpServletRequest request) {
        slackService.sendSlackAlertLog(exception, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrorResponse());
    }
}
