package org.system.librarymanagementsystemjava.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.system.librarymanagementsystemjava.key.CompositeKey;
import org.system.librarymanagementsystemjava.service.DepartmentService;

import java.util.List;
import java.util.Set;
@Component
@Entity
@Table(name="department_tbl")
public class Department {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;

    public Department() {
    }
    public Department(int id,String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
