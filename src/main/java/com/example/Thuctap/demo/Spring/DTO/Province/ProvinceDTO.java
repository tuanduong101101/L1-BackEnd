package com.example.Thuctap.demo.Spring.DTO.Province;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProvinceDTO {
    private String name ;
    private List<DistrictDTO> dtoList;
}
