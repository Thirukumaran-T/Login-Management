package com.example.LoginManagement.Service;

import com.example.LoginManagement.Entity.*;
import com.example.LoginManagement.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceManagementService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceCategoryRepository categoryRepository;

    @Autowired
    private StaffServiceRepository staffServiceRepository;

    @Autowired
    private UserRepository userRepository;

    // ===== Category Management =====
    public ServiceCategory addCategory(ServiceCategory category) {
        return categoryRepository.save(category);
    }

    public List<ServiceCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ===== Service Management =====
    public ServiceEntity addService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceEntity updateService(Long id, ServiceEntity updated) {
        ServiceEntity existing = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        existing.setName(updated.getName());
        existing.setDurationMinutes(updated.getDurationMinutes());
        existing.setPrice(updated.getPrice());
        existing.setDescription(updated.getDescription());
        existing.setCategory(updated.getCategory());
        return serviceRepository.save(existing);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    // ===== Staff-Service Mapping =====
    public StaffService assignServiceToStaff(Long staffId, Long serviceId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        StaffService ss = new StaffService();
        ss.setStaff(staff);
        ss.setService(service);
        return staffServiceRepository.save(ss);
    }

    public List<StaffService> getServicesByStaff(Long staffId) {
        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return staffServiceRepository.findByStaff(staff);
    }
}