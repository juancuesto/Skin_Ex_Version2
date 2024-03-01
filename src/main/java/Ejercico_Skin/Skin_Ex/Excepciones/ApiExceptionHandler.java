package Ejercico_Skin.Skin_Ex.Excepciones;

import Ejercico_Skin.Skin_Ex.dto.ErrorDto;
import Ejercico_Skin.Skin_Ex.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> notFoundException(NotFoundException ex){
        ErrorDto error=ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({BadRequestException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.servlet.resource.NoResourceFoundException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageConversionException.class,
            org.springframework.web.servlet.resource.NoResourceFoundException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.dao.InvalidDataAccessApiUsageException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> badRequestException(Throwable ex){
        ErrorDto error=ErrorDto.builder().code("p-500").message("el campo Id no se ha introducido o su formato no es el correcto").build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



}
