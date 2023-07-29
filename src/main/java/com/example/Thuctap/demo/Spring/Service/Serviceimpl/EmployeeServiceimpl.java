package com.example.Thuctap.demo.Spring.Service.Serviceimpl;

import com.example.Thuctap.demo.Spring.Annotation.Relative;
import com.example.Thuctap.demo.Spring.Annotation.ValidateRelative;
import com.example.Thuctap.demo.Spring.DTO.Degree.Degree1DTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.Employee1DTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeDTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeSearchDTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeUpdateDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.Degree;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import com.example.Thuctap.demo.Spring.Exception.ValidateErrorResponse;
import com.example.Thuctap.demo.Spring.Mapper.DegreeMapper;
import com.example.Thuctap.demo.Spring.Mapper.EmployeeMapper;
import com.example.Thuctap.demo.Spring.Repository.*;
import com.example.Thuctap.demo.Spring.Service.EmployeeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.*;

@Service
public class EmployeeServiceimpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private DegreeMapper degreeMapper;


    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setCode(employeeDTO.getCode());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setAge(employeeDTO.getAge());
        employee.setProvinceId(employeeDTO.getProvinceId());
        List<District> districts = districtRepository.findByProvinceId(employeeDTO.getProvinceId());
        List<Commune> communes = communeRepository.findByDistrictId(employeeDTO.getDistrictId());
        Optional<District> district1 = districts.stream().filter(district2 -> district2.getId().equals(employeeDTO.getDistrictId())).findAny();
        Optional<Commune> commune = communes.stream().filter(commune1 -> commune1.getId().equals(employeeDTO.getCommuneId())).findAny();
        if (district1.isPresent()) {
            employee.setDistrictId(employeeDTO.getDistrictId());
            if (commune.isPresent()) {
                employee.setCommuneId(employeeDTO.getCommuneId());
            } else {
                throw new NoSuchElementException("Xã không thuộc huyện");
            }
        } else {
            throw new NoSuchElementException("Huyện không thuộc tỉnh");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getListEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployee(EmployeeSearchDTO employeeSearchDTO) {
        return employeeRepository.findEmployeeByIdAndCode(employeeSearchDTO.getId(), employeeSearchDTO.getCode());
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // kiểm tra huyện phải thuộc tỉnh và xã phải thuộc huyện
    public boolean check(Long provineId, Long districtId, Long communeId) {
        List<District> districts = districtRepository.findByProvinceId(provineId);
        List<Commune> communes = communeRepository.findByDistrictId(districtId);
        Optional<District> district1 = districts.stream().filter(district2 -> district2.getId().equals(districtId)).findAny();
        Optional<Commune> commune = communes.stream().filter(commune1 -> commune1.getId().equals(communeId)).findAny();
        if (district1.isPresent()) {

            if (commune.isPresent()) {

            } else {
//                throw new NoSuchElementException("Xã không thuộc huyện");
                return false;
            }
        } else {
//            throw new NoSuchElementException("Huyện không thuộc tỉnh");
            return false;
        }
        return true;
    }

    @Override
    public void getFile(HttpServletResponse response) throws IOException {
        List<Employee> employees = employeeRepository.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employee Information");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("STT");
        row.createCell(1).setCellValue("Tên");
        row.createCell(2).setCellValue("Mã");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("SĐT");
        row.createCell(5).setCellValue("Tuổi");
        int index = 1;
        for (Employee employee : employees) {
            HSSFRow row1 = sheet.createRow(index);
            row1.createCell(0).setCellValue(employee.getId());
            row1.createCell(1).setCellValue(employee.getName());
            row1.createCell(2).setCellValue(employee.getCode());
            row1.createCell(3).setCellValue(employee.getEmail());
            row1.createCell(4).setCellValue(employee.getPhone());
            row1.createCell(5).setCellValue(employee.getAge());
            index++;
        }
        ServletOutputStream sos = response.getOutputStream();
        workbook.write(sos);
        workbook.close();
        sos.close();
    }

    @Override
    public Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto, Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Employee employee1 = new Employee();
        if (employeeUpdateDto.getProvinceId() != null
                && employeeUpdateDto.getDistrictId() != null
                && employeeUpdateDto.getCommuneId() != null) {
            if (check(employeeUpdateDto.getProvinceId(), employeeUpdateDto.getDistrictId(), employeeUpdateDto.getCommuneId())) {
                employee1.setId(employee.get().getId());
                employee1.setCode(employeeUpdateDto.getCode());
                employee1.setName(employeeUpdateDto.getName());
                employee1.setEmail(employeeUpdateDto.getEmail());
                employee1.setEmail(employeeUpdateDto.getEmail());
                employee1.setAge(employeeUpdateDto.getAge());
                employee1.setPhone(employeeUpdateDto.getPhone());
                employee1.setProvinceId(employeeUpdateDto.getProvinceId());
                employee1.setDistrictId(employeeUpdateDto.getDistrictId());
                employee1.setCommuneId(employeeUpdateDto.getCommuneId());
            }
            return employeeRepository.save(employee1);
        } else {
            employee1.setId(employee.get().getId());
            employee1.setCode(employeeUpdateDto.getCode());
            employee1.setName(employeeUpdateDto.getName());
            employee1.setEmail(employeeUpdateDto.getEmail());
            employee1.setAge(employeeUpdateDto.getAge());
            employee1.setPhone(employeeUpdateDto.getPhone());
            employee1.setProvinceId(employee.get().getProvinceId());
            employee1.setDistrictId(employee.get().getDistrictId());
            employee1.setCommuneId(employee.get().getCommuneId());
        }
        return employeeRepository.save(employee1);
    }

    @Override
    // trả về danh sách có chứa văn bằng
    public List<Employee1DTO> getEmployeeContainDegree() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee1DTO> employee1DTOS = new ArrayList<>();
        for (Employee employee : employees) {
            List<Degree> degrees = degreeRepository.findByEmployeeId(employee.getId());
            List<Degree1DTO> degree1DTOS = new ArrayList<>();
            for (Degree degree : degrees) {
                degree1DTOS.add(degreeMapper.toDegreeDTO(degree));
            }
            Employee1DTO employee1DTO = new Employee1DTO(employee.getId(), employee.getName(), employee.getCode()
                    , employee.getAge(), employee.getEmail(),
                    employee.getPhone(), employee.getProvinceId(),
                    employee.getDistrictId(), employee.getCommuneId(),
                    degree1DTOS);
            employee1DTOS.add(employee1DTO);
        }
        return employee1DTOS;
    }

    public boolean validateEmployee(EmployeeDTO employeeDTO) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeDTO);
        if (!violations.isEmpty() || !check(employeeDTO.getProvinceId(), employeeDTO.getDistrictId(), employeeDTO.getCommuneId())) {
            return false;
        }
        return true;
    }

    @Override
    public ValidateErrorResponse importListEmployee(MultipartFile multipartFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int rowIndex = 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Row row : sheet) {
            if (rowIndex == 0) {
                rowIndex++;
                continue;
            }
            Employee employee = new Employee();
            for (Cell cell : row) {
                switch (cell.getColumnIndex()) {
                    case 0:
                        employee.setCode(cell.getStringCellValue());
                        break;
                    case 1:
                        employee.setName(cell.getStringCellValue());
                        break;
                    case 2:
                        employee.setEmail(cell.getStringCellValue());
                        break;
                    case 3:
                        employee.setPhone(cell.getStringCellValue());
                        break;
                    case 4:
                        employee.setAge((int) cell.getNumericCellValue());
                        break;
                    case 5:
                        employee.setProvinceId((long) cell.getNumericCellValue());
                        break;
                    case 6:
                        employee.setDistrictId((long) (cell.getNumericCellValue()));
                        break;
                    case 7:
                        employee.setCommuneId((long) cell.getNumericCellValue());
                        break;
                }
            }
            map.put(rowIndex, employee);
            rowIndex++;
        }
        Map<Integer, Employee> map1 = new HashMap<>();
        for (Map.Entry<Integer, Employee> entry : map.entrySet()) {
            if (validateEmployee(employeeMapper.toEmployeeDTO(entry.getValue()))) {
                employeeRepository.save(entry.getValue());
            } else {
                map1.put(entry.getKey(), entry.getValue());
            }
        }
        if (map1.isEmpty()) {
            return null;
        } else {
            ValidateErrorResponse errorResponse = new ValidateErrorResponse();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            for (Map.Entry<Integer, Employee> entry1 : map1.entrySet()) {
                List<Map<Integer, String>> list = new ArrayList<>();
                Set<ConstraintViolation<EmployeeDTO>> violations = validator.validate(employeeMapper.toEmployeeDTO(entry1.getValue()));
                for (ConstraintViolation violation : violations) {
                    Map<Integer, String> map2 = new HashMap<>();
                    map2.put(hashCode(), violation.getMessage());
                    list.add(map2);
                }
                if (!check(entry1.getValue().getProvinceId(), entry1.getValue().getDistrictId(), entry1.getValue().getCommuneId())) {
                    Map<Integer, String> map3 = new HashMap<>();
                    map3.put(hashCode(), "Quan hệ giữa tỉnh huyện xã chưa đúng !");
                    list.add(map3);
                }

                errorResponse.getViolation().put("Lỗi ở dòng " + entry1.getKey(), list);
            }
            return errorResponse;


        }
    }
}


