package com.example.Thuctap.demo.Spring.Service;

import com.example.Thuctap.demo.Spring.DTO.Degree.DegreeDTO;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.Degree;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DegreeService {
    Degree addDegree(DegreeDTO degreeDTO);

}
