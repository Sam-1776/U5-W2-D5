package samuelesimeone.GestioneDispositiviAziendali.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handle400(BadRequestException bd){
        if (!bd.getErrorList().isEmpty()){
            List<String> errorList = bd.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsPayloadList(bd.getMessage(), LocalDateTime.now(), errorList);
        }
        return new ErrorsPayload(bd.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handle404(NotFoundException nt){
        return new ErrorsPayload(nt.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload generics500(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayload("Problema lato Server!!", LocalDateTime.now());
    }
}
