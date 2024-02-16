package samuelesimeone.GestioneDispositiviAziendali.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record EmployeeDTO(
        @NotEmpty(message = "Lo username è obbligatorio")
         String username,
        @NotEmpty(message = "Il nome è obbligatorio")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
        String surname,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "Mail inserita non valida")
        String email
) {
}
