package com.example.Thuctap.demo.Spring.Annotation;

import com.example.Thuctap.demo.Spring.BeanConfiguration.ContextProvider;
import com.example.Thuctap.demo.Spring.DTO.Employee.Employee1DTO;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeDTO;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Repository.CommuneRepository;
import com.example.Thuctap.demo.Spring.Repository.DistrictRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
@Service
@Validated
public class ValidateRelative implements ConstraintValidator<Relative, EmployeeDTO> {
    public ValidateRelative(DistrictRepository districtRepository, CommuneRepository communeRepository) {
        this.districtRepository = districtRepository;
        this.communeRepository = communeRepository;
    }

//    @Autowired
    private DistrictRepository districtRepository ;
//    @Autowired
    private CommuneRepository communeRepository;

    @Override
    public boolean isValid(EmployeeDTO employeeDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<District> districts = districtRepository.findByProvinceId(employeeDTO.getProvinceId());
        List<Commune> communes = communeRepository.findByDistrictId(employeeDTO.getDistrictId());
        Optional<District> district1 = districts.stream().filter(district2 -> district2.getId().equals(employeeDTO.getDistrictId())).findAny();
        Optional<Commune> commune = communes.stream().filter(commune1 -> commune1.getId().equals(employeeDTO.getCommuneId())).findAny();
        if (district1.isPresent()) {
            if (commune.isPresent()) {
            } else {
//                throw new NoSuchElementException("Xã không thuộc huyện");
                return false ;
            }
        } else {
//            throw new NoSuchElementException("Huyện không thuộc tỉnh");
            return false ;
        }
        return true;
    }
}
