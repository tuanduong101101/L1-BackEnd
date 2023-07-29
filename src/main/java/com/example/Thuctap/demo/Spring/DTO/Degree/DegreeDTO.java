package com.example.Thuctap.demo.Spring.DTO.Degree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DegreeDTO {
    private String name ;
    private LocalDate startDay;
    private LocalDate endDay;
    private Long employeeId;
    private Long provinceId;
}
