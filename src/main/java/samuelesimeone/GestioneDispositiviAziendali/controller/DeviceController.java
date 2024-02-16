package samuelesimeone.GestioneDispositiviAziendali.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import samuelesimeone.GestioneDispositiviAziendali.dto.DeviceDTO;
import samuelesimeone.GestioneDispositiviAziendali.dto.EmployeeDTO;
import samuelesimeone.GestioneDispositiviAziendali.entities.Device;
import samuelesimeone.GestioneDispositiviAziendali.entities.Employee;
import samuelesimeone.GestioneDispositiviAziendali.exceptions.BadRequestException;
import samuelesimeone.GestioneDispositiviAziendali.services.DeviceService;

import java.util.UUID;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping
    public Page<Device> getAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String order){
        return this.deviceService.getAll(page, size, order);
    }

    @GetMapping("/{id}")
    public Device getAutoreById(@PathVariable UUID id){
        return this.deviceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device save(@RequestBody @Validated DeviceDTO device, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.deviceService.save(device);
    }

    @PutMapping("/{id}")
    public Device update(@PathVariable UUID id, @RequestBody DeviceDTO deviceUp){
        return this.deviceService.update(id, deviceUp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        this.deviceService.delete(id);
    }
}
