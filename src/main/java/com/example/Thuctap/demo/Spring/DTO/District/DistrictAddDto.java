package com.example.Thuctap.demo.Spring.DTO.District;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DistrictAddDto {
    private Long provinceID;
    private String districtName;

}
