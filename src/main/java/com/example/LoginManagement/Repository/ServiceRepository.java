package com.example.LoginManagement.Repository;

import com.example.LoginManagement.Entity.ServiceEntity;
import com.example.LoginManagement.Entity.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByCategory(ServiceCategory category);
}
