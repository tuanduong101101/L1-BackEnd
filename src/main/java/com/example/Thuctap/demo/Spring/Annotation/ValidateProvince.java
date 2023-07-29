package com.example.Thuctap.demo.Spring.Annotation;

import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Repository.ProvinceRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

public class ValidateProvince implements ConstraintValidator<ValidProvince,Long>{
        @Autowired
        private ProvinceRepository provinceRepository ;
        @Override
        public boolean isValid(Long provinceId, ConstraintValidatorContext context) {
            Optional<Province> province = provinceRepository.findById(provinceId);
            if (province.isPresent()) {
                return true;
            } else
                return false;
        }

}
