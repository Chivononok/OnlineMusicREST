package onlineMusic.exceptions;

import jakarta.validation.ConstraintViolationException;
import onlineMusic.exceptions.model.ErrorDto;
import onlineMusic.exceptions.model.ValidationErrorResponse;
import onlineMusic.exceptions.model.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException ex){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setErrorCode("1");
        errorDto.setErrorMessage(ex.getMessage());
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(NotUniqueUserException.class)
    ResponseEntity<ErrorDto> notUniqueUserHandler(NotUniqueUserException ex){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setErrorCode("2");

        errorDto.setErrorMessage(ex.getMessage());
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
        return responseEntity;
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDto> handleInvalidToken(InvalidTokenException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("3");
        errorDto.setErrorMessage(ex.getMessage());
        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
        return responseEntity;
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<ErrorDto> songNotFoundHandler(Exception ex){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorCode("9999");
        errorDto.setErrorMessage((ex.getMessage()));
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
