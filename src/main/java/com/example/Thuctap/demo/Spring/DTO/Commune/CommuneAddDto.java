package com.example.Thuctap.demo.Spring.DTO.Commune;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// request body 1 commune
public class CommuneAddDto {
    private String districtName;
    private String communeName;
}
