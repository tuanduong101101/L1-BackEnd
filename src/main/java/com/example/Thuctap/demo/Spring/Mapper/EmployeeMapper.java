package com.example.Thuctap.demo.Spring.Mapper;

import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeDTO;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEmployeeDTO(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setCode(employeeDTO.getCode());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setAge(employeeDTO.getAge());
        return employee;
    }
    public EmployeeDTO toEmployeeDTO (Employee employee ){
        EmployeeDTO employeeDTO = new EmployeeDTO() ;
        employeeDTO.setCode(employee.getCode());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setProvinceId(employee.getProvinceId());
        employeeDTO.setDistrictId(employee.getDistrictId());
        employeeDTO.setCommuneId(employee.getCommuneId());
        return employeeDTO;
    }
}
