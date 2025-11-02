package com.example.LoginManagement.Controller;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Service.ServiceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceManagementController {

    @Autowired
    private ServiceManagementService serviceManagementService;

    // ===== Category Endpoints =====
    @PostMapping("/category")
    public ServiceCategory addCategory(@RequestBody ServiceCategory category) {
        return serviceManagementService.addCategory(category);
    }

    @GetMapping("/categories")
    public List<ServiceCategory> getAllCategories() {
        return serviceManagementService.getAllCategories();
    }

    // ===== Service Endpoints =====
    @PostMapping
    public ServiceEntity addService(@RequestBody ServiceEntity service) {
        return serviceManagementService.addService(service);
    }

    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return serviceManagementService.getAllServices();
    }

    @PutMapping("/{id}")
    public ServiceEntity updateService(@PathVariable Long id, @RequestBody ServiceEntity service) {
        return serviceManagementService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceManagementService.deleteService(id);
        return "Service deleted successfully";
    }

    // ===== Staff-Service Mapping =====
    @PostMapping("/assign/{staffId}/{serviceId}")
    public StaffService assignServiceToStaff(@PathVariable Long staffId, @PathVariable Long serviceId) {
        return serviceManagementService.assignServiceToStaff(staffId, serviceId);
    }

    @GetMapping("/staff/{staffId}")
    public List<StaffService> getServicesByStaff(@PathVariable Long staffId) {
        return serviceManagementService.getServicesByStaff(staffId);
    }
}
