package com.example.Thuctap.demo.Spring.Mapper;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import com.example.Thuctap.demo.Spring.Entity.District;
import org.springframework.stereotype.Component;

@Component
public class DistrictMapper {
    public DistrictDTO toDistrictDTO(District district){
        DistrictDTO districtDTO = new DistrictDTO(district.getId(),district.getName());
        return districtDTO;


    }
}
