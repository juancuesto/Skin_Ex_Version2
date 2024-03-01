package Ejercico_Skin.Skin_Ex.dto;

public class ErrorMessage {

    private Exception exception;
    private String message;
    private  String path;

    public ErrorMessage(Exception exception, String path) {
        this.exception = exception;
        this.message = exception.getMessage();
        this.path = path;
    }

    public Exception getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "exception=" + exception +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
