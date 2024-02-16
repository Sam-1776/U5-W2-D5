package samuelesimeone.GestioneDispositiviAziendali.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String profilePic;
    @OneToMany(mappedBy = "employee")
    private List<Device> devices;

    public Employee(String username, String name, String surname, String email, String profilePic) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.profilePic = profilePic;
    }
}
