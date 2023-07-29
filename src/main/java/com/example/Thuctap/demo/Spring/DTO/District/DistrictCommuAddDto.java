package com.example.Thuctap.demo.Spring.DTO.District;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictCommuAddDto {
    private String districtName ;
    private Long provinceID;
    private List<CommuneDto> communeDtos ;
}
