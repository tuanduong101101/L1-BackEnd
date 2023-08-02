package com.example.Thuctap.demo.Spring.Service;

import com.example.Thuctap.demo.Spring.DTO.Employee.Employee1DTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeDTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeSearchDTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeUpdateDto;
import com.example.Thuctap.demo.Spring.DTO.PDMDto;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import com.example.Thuctap.demo.Spring.Exception.ValidateErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO ) ;
    List<Employee> getListEmployee();
    Employee findEmployee(EmployeeSearchDTO employeeSearchDTO );
    // xóa nhân viên
    void deleteEmployee(Long id);
    void getFile(HttpServletResponse response) throws IOException;
    // cập nhật thông tin nhân viên
    Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto, Long employeeId);
    // lấy ra danh sách nhân viên bao gồm bằng
    List<Employee1DTO> getEmployeeContainDegree();
    // validate listEmloyee imported from user and response error if have
    ValidateErrorResponse importListEmployee(MultipartFile multipartFile) throws IOException, InvalidFormatException;


}
