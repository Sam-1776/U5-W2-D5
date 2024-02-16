package samuelesimeone.GestioneDispositiviAziendali.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Tablet extends Device{
    private String name;

    public Tablet(String state, String numberSeries, String name) {
        super(state, numberSeries);
        this.name = name;
    }
}
