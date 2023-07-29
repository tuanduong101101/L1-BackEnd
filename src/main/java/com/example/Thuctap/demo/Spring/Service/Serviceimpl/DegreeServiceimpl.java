package com.example.Thuctap.demo.Spring.Service.Serviceimpl;

import com.example.Thuctap.demo.Spring.DTO.Degree.DegreeDTO;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.Degree;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Repository.DegreeRepository;
import com.example.Thuctap.demo.Spring.Repository.EmployeeRepository;
import com.example.Thuctap.demo.Spring.Repository.ProvinceRepository;
import com.example.Thuctap.demo.Spring.Service.DegreeService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DegreeServiceimpl implements DegreeService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DegreeRepository degreeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    // thêm văn bằng cho nhân viên
    public Degree addDegree(DegreeDTO degreeDTO) {
        Optional<Employee> employee = employeeRepository.findById(degreeDTO.getEmployeeId());
        Optional<Province> province = provinceRepository.findProvinceById(degreeDTO.getProvinceId());
        Optional<Degree> degree = degreeRepository.findByProvinceIdAndEmployeeIdAndName(degreeDTO.getProvinceId(),
                degreeDTO.getEmployeeId(), degreeDTO.getName());
        Degree degree1 = new Degree();
        List<Degree> degrees = degreeRepository.getListDegreeValidate(degreeDTO.getName(),LocalDate.now());
        if(degrees.size() >= 3 ){
            throw new DuplicateKeyException("Employee đã có đủ 3 văn bằng loại này và còn hiệu lực");
        }
        else {
            if (degree.isPresent() && degree.get().getEndDay().isAfter(LocalDate.now())) {
                throw new DuplicateKeyException("Đã tồn tại văn bằng và vẫn còn hiệu lực !");
            } else if (degree.isPresent() && degree.get().getEndDay().isBefore(LocalDate.now())) {
                degreeRepository.deleteById(degree.get().getId());
                degree1.setName(degreeDTO.getName());
                degree1.setStartDay(degreeDTO.getStartDay());
                degree1.setEndDay(degreeDTO.getEndDay());
                degree1.setEmployee(employee.get());
                degree1.setProvince(province.get());
                return degreeRepository.save(degree1);
            } else {
                degree1.setName(degreeDTO.getName());
                degree1.setStartDay(degreeDTO.getStartDay());
                degree1.setEndDay(degreeDTO.getEndDay());
                degree1.setEmployee(employee.get());
                degree1.setProvince(province.get());
            }
        }

        return degreeRepository.save(degree1);
    }
}

