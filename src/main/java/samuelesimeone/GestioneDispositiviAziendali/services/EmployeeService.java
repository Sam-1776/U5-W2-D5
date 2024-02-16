package samuelesimeone.GestioneDispositiviAziendali.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import samuelesimeone.GestioneDispositiviAziendali.dao.DeviceDAO;
import samuelesimeone.GestioneDispositiviAziendali.dao.EmployeeDAO;
import samuelesimeone.GestioneDispositiviAziendali.dto.EmployeeDTO;
import samuelesimeone.GestioneDispositiviAziendali.entities.Device;
import samuelesimeone.GestioneDispositiviAziendali.entities.Employee;
import samuelesimeone.GestioneDispositiviAziendali.entities.State;
import samuelesimeone.GestioneDispositiviAziendali.exceptions.BadRequestException;
import samuelesimeone.GestioneDispositiviAziendali.exceptions.NotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    Cloudinary cloudinary;

    @Autowired
    DeviceService deviceService;

    @Autowired
    DeviceDAO deviceDAO;

    public Page<Employee> getAll(int pageN, int pageS, String OrderBY){
        Pageable pageable = PageRequest.of(pageN, pageS, Sort.by(OrderBY));
        return employeeDAO.findAll(pageable);
    }

    public Employee save(EmployeeDTO employee){
        employeeDAO.findByEmail(employee.email()).ifPresent(element -> {
            throw new BadRequestException("Email inserita già in uso riprovare");
        });
        String avatar = "https://ui-avatars.com/api/?name=" + employee.name() + "+" + employee.surname();
        Employee newEmployee = new Employee(employee.username(), employee.name(), employee.surname(), employee.email(), avatar);
        return employeeDAO.save(newEmployee);
    }

    public Employee findById(UUID id){
        return employeeDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Employee update(UUID id, EmployeeDTO emmployeeUp){
        Employee found = this.findById(id);
        found.setUsername(emmployeeUp.username());
        found.setName(emmployeeUp.name());
        found.setSurname(emmployeeUp.surname());
        found.setEmail(emmployeeUp.email());
        found.setProfilePic(found.getProfilePic());
        found.setDevices(found.getDevices());
        return employeeDAO.save(found);
    }

    public void delete(UUID id){
        Employee found = this.findById(id);
        employeeDAO.delete(found);
    }

    public Employee uploadProfilePic(UUID id, MultipartFile image) throws IOException {
        Employee found = this.findById(id);
        String profilePic = (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setUsername(found.getUsername());
        found.setName(found.getName());
        found.setSurname(found.getSurname());
        found.setEmail(found.getEmail());
        found.setProfilePic(profilePic);
        found.setDevices(found.getDevices());
        return employeeDAO.save(found);
    }

    public Employee assignDevice(UUID id, UUID deviceID) throws Exception {
        Employee employee = this.findById(id);
        List<Device> devices = new ArrayList<>();
        if (!employee.getDevices().isEmpty()){
            devices.addAll(employee.getDevices());
        }
        Device device = deviceService.findById(deviceID);
        if (!device.getState().equals(State.DISPONIBILE)){
            throw new Exception();
        }
        device.setState(State.ASSEGNATO);
        device.setNumberSeries(device.getNumberSeries());
        device.setType(device.getType());
        device.setEmployee(employee);
        devices.add(device);
        employee.setDevices(devices);
        employee.setUsername(employee.getUsername());
        employee.setName(employee.getName());
        employee.setSurname(employee.getSurname());
        employee.setEmail(employee.getEmail());
        employee.setProfilePic(employee.getProfilePic());
        deviceDAO.save(device);
        return employeeDAO.save(employee);
    }
}
