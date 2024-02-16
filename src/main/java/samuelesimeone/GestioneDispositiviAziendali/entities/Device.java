package samuelesimeone.GestioneDispositiviAziendali.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Device {
    @Id
    @GeneratedValue
    private UUID id;
    private String state;
    private String numberSeries;
    @ManyToOne
    private Employee employee;

    public Device(String state, String numberSeries) {
        this.state = state;
        this.numberSeries = numberSeries;
    }
}
