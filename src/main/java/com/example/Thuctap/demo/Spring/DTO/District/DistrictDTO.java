package com.example.Thuctap.demo.Spring.DTO.District;

import com.example.Thuctap.demo.Spring.Entity.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DistrictDTO {
    private Long districtId;
    private String name;

}