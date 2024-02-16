package samuelesimeone.GestioneDispositiviAziendali.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException{
    List<ObjectError> errorList;
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList){
        super("Errori nel payload");
        this.errorList = errorList;
    }
}
