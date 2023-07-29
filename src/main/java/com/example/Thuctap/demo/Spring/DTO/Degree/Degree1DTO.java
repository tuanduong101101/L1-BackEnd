package com.example.Thuctap.demo.Spring.DTO.Degree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Degree1DTO {
    private String name ;
    private LocalDate startDay;
    private LocalDate endDay;
    private Long provinceId;
}
