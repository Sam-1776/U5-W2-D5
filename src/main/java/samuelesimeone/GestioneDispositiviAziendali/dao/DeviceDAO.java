package samuelesimeone.GestioneDispositiviAziendali.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samuelesimeone.GestioneDispositiviAziendali.entities.Device;

import java.util.UUID;

@Repository
public interface DeviceDAO extends JpaRepository<Device, UUID> {
}
