package onlineMusic.exceptions;

import onlineMusic.exceptions.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvise {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException ex){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setErrorCode("1");
        errorDto.setErrorMessage(ex.getMessage());
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
//        ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto).h
//        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
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

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
    ResponseEntity<ErrorDto> songNotFoundHandler(Exception ex){
        ErrorDto errorDto = new ErrorDto();
        String s =   SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        errorDto.setErrorCode("9999");
        errorDto.setErrorMessage((ex.getMessage()));
        ResponseEntity<ErrorDto> responseEntity=new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
