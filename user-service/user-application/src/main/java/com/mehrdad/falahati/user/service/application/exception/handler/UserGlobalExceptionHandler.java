package com.mehrdad.falahati.user.service.application.exception.handler;

import com.mehrdad.falahati.system.application.handler.ErrorDto;
import com.mehrdad.falahati.system.application.handler.GlobalExceptionHandler;
import com.mehrdad.falahati.common.domain.exception.NotFoundException;
import com.mehrdad.falahati.user.service.domain.exception.UserDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(UserDomainException userDomainException) {
        log.error(userDomainException.getMessage(), userDomainException);
        return ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(userDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(NotFoundException notFoundException) {
        log.error(notFoundException.getMessage(), notFoundException);
        return ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(notFoundException.getMessage())
                .build();
    }
}
