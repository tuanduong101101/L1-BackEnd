package com.example.Thuctap.demo.Spring.Controller;

import com.example.Thuctap.demo.Spring.DTO.Employee.Employee1DTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeDTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeSearchDTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeUpdateDto;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import com.example.Thuctap.demo.Spring.Repository.CommuneRepository;
import com.example.Thuctap.demo.Spring.Repository.DistrictRepository;
import com.example.Thuctap.demo.Spring.Repository.ProvinceRepository;
import com.example.Thuctap.demo.Spring.Service.EmployeeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService ;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository ;
    @Autowired
    private CommuneRepository communeRepository ;
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
            Employee employee = employeeService.addEmployee(employeeDTO);
            return employee;
        }

    @GetMapping("/list")
    public List<Employee> getListEmployee(){
        return employeeService.getListEmployee();
    }
    @GetMapping("/search")
    public Employee getSearchListEmployee(@RequestBody @Valid EmployeeSearchDTO employeeSearchDTO ){
        return employeeService.findEmployee(employeeSearchDTO);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/getFile")
    public void getFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=MyExcel.xls");
        employeeService.getFile(response);
    }
    @PostMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody @Valid EmployeeUpdateDto employeeUpdateDto, @PathVariable("id") Long Id){
        Employee employee = employeeService.updateEmployee(employeeUpdateDto,Id);
        return employee;
    }
    // lấy ra danh sách nhân viên có chứa văn bằng
    @GetMapping("/listEmployee")
    public List<Employee1DTO> getList(){
        List<Employee1DTO> employee1DTOS = employeeService.getEmployeeContainDegree();
        return employee1DTOS;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadListEmployee(@RequestParam("file") MultipartFile multipartFile) throws IOException, InvalidFormatException {
        return  ResponseEntity.ok(employeeService.importListEmployee(multipartFile));
    }

}
