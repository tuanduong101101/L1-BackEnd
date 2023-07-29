package com.example.Thuctap.demo.Spring.Mapper;

import com.example.Thuctap.demo.Spring.DTO.Degree.Degree1DTO;
import com.example.Thuctap.demo.Spring.DTO.Degree.DegreeDTO;
import com.example.Thuctap.demo.Spring.Entity.Degree;
import org.springframework.stereotype.Component;

@Component
public class DegreeMapper {
    public Degree1DTO toDegreeDTO(Degree degree){
        Degree1DTO degree1DTO = new Degree1DTO(degree.getName(),degree.getStartDay(),degree.getEndDay(),degree.getProvince().getId());
        return degree1DTO;
    }
}
