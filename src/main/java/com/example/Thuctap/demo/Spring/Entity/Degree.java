package com.example.Thuctap.demo.Spring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "degree")
public class Degree  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id ;
    @Column
    private String name ;
    @Column
    private LocalDate startDay ;
    @Column
    private LocalDate endDay ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province ;


}
