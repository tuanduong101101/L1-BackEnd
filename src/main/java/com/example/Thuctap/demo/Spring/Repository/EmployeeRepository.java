package com.example.Thuctap.demo.Spring.Repository;

import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findEmployeeByIdAndCode(Long id, String code);




}
