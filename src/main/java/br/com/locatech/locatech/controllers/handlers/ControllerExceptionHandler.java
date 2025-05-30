package br.com.locatech.locatech.controllers.handlers;

import br.com.locatech.locatech.dtos.ResourceNotFoundDTO;
import br.com.locatech.locatech.dtos.ValidationErrorDTO;
import br.com.locatech.locatech.exceptions.CancelamentoInvalidoException;
import br.com.locatech.locatech.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(ex.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (var error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }

    @ExceptionHandler(CancelamentoInvalidoException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerCancelamentoInvalidoException(CancelamentoInvalidoException ex) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(ex.getMessage(), status.value()));
    }

}
