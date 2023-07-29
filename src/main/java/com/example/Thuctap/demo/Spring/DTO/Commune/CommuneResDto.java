package com.example.Thuctap.demo.Spring.DTO.Commune;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
// them cummune xac dinh luon thuoc district nao
public class CommuneResDto {
    private Long communeId;
    private String communeName;
    private DistrictDTO districtDTO ;
}
