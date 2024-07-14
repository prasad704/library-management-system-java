package org.system.librarymanagementsystemjava.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.librarymanagementsystemjava.model.Department;

import java.util.Optional;

public interface DepartmentService extends JpaRepository<Department, Integer> {
    Optional<Department> getIdByDepartmentName(String departmentName);
}
