package ru.itis.studentsgiftery.controllers.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.studentsgiftery.exceptions.ExceptionEntity;
import ru.itis.studentsgiftery.exceptions.ForbiddenException;
import ru.itis.studentsgiftery.exceptions.LowBalanceException;
import ru.itis.studentsgiftery.exceptions.StudentsNotFoundException;
import ru.itis.studentsgiftery.validation.http.ValidationErrorDto;
import ru.itis.studentsgiftery.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ValidationExceptionResponse> handleException(MethodArgumentNotValidException exception){
        List<ValidationErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else if (error instanceof ObjectError) {
                String objectName = error.getObjectName();
                errorDto.setObject(objectName);
            }
            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationExceptionResponse.builder()
                .errors(errors)
                .build());

    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> StudentsNotFoundExceptionHandler(StudentsNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionEntity.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> LowBalanceExceptionHandler(LowBalanceException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionEntity.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionEntity> ForbiddenExceptionHandler(ForbiddenException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionEntity.builder().message(exception.getMessage()).build());
    }
}
