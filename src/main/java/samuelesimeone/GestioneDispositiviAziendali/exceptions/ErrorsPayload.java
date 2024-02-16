package samuelesimeone.GestioneDispositiviAziendali.exceptions;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsPayload {
    private String message;
    private LocalDateTime time;
}
