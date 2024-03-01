package Ejercico_Skin.Skin_Ex.Excepciones;

import org.springframework.http.HttpStatus;

public class BadRequestExceptionV2 extends RuntimeException {

    private String code;
    private HttpStatus status;

    public BadRequestExceptionV2(String code, HttpStatus status,String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
