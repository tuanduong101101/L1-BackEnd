package com.example.Thuctap.demo.Spring.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.ObjectIdGenerators.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "district")

public class District  {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name = "district_name")
    private String name ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province ;



}
