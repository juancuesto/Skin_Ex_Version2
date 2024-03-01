package Ejercico_Skin.Skin_Ex.Excepciones;

import lombok.Data;
import org.springframework.http.HttpStatus;

//@Data
public class BadRequestException extends Exception{

   // private static final String DESCRIPTION="Bad Request Exception (400)";
   private ExceptionDetails details;

    public BadRequestException(String message, ExceptionDetails details) {
        super(message);
        this.details = details;
    }

    public ExceptionDetails getDetails() {
        return details;
    }

    public void setDetails(ExceptionDetails details) {
        this.details = details;
    }
}
