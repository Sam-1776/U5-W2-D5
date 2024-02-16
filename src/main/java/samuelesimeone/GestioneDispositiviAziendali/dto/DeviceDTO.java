package samuelesimeone.GestioneDispositiviAziendali.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import samuelesimeone.GestioneDispositiviAziendali.entities.Employee;

public record DeviceDTO(
        @NotEmpty(message = "Lo stato è obbligatorio")
        String state,
        @NotEmpty(message = "Il numero di serie è obbligatorio")
        String numberSeries,
        @NotEmpty(message = "Il tipo è obbligatorio")
        String type
) {
}
