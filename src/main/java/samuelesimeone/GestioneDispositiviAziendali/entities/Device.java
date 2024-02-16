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
public class Device {
    @Id
    @GeneratedValue
    private UUID id;
    private String state;
    private String numberSeries;
    @ManyToOne
    private Employee employee;
    private String type;

    public Device(String state, String numberSeries) {
        this.state = state;
        this.numberSeries = numberSeries;
    }
}
