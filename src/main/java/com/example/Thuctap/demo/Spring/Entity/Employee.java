package com.example.Thuctap.demo.Spring.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column
    private String code;
    @Column
    private String name ;
    @Column
    private String email;
    @Column
    private String phone ;
    @Column
    private Integer age ;
    @Column
    private Long provinceId;
    @Column
    private Long districtId;
    @Column
    private Long communeId;




}
