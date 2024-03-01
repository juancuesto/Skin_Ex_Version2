package Ejercico_Skin.Skin_Ex.Excepciones;

public class ExceptionDetails {

    private String usermessage;
    private String code;

    public ExceptionDetails(String usermessage, String code) {
        this.usermessage = usermessage;
        this.code = code;
    }

    public String getUsermessage() {
        return usermessage;
    }

    public void setUsermessage(String usermessage) {
        this.usermessage = usermessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
