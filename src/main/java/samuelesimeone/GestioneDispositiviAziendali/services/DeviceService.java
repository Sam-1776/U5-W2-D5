package samuelesimeone.GestioneDispositiviAziendali.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import samuelesimeone.GestioneDispositiviAziendali.dao.DeviceDAO;
import samuelesimeone.GestioneDispositiviAziendali.entities.Device;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    DeviceDAO deviceDAO;

    public Page<Device> getAll(int pageN, int pageS, String orderBy){
        Pageable pageable = PageRequest.of(pageN, pageS, Sort.by(orderBy));
        return deviceDAO.findAll(pageable);
    }

}
