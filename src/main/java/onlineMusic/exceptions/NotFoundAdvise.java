package onlineMusic.exceptions;

import onlineMusic.exceptions.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class NotFoundAdvise {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException ex){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setErrorCode("1");
        errorDto.setErrorMessage(ex.getMessage());
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//        ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto).h
//        SecurityContextHolder.getContext().getAuthentication().getName();
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
}
