package com.example.Thuctap.demo.Spring.DTO.Province;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceResDto {
    private Long provinceId ;
    private String provinceName;
    List<DistrictDTO> districtList;
}
