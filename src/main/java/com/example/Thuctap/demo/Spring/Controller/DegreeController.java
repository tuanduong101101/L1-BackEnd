package com.example.Thuctap.demo.Spring.Controller;

import com.example.Thuctap.demo.Spring.DTO.Degree.DegreeDTO;
import com.example.Thuctap.demo.Spring.Entity.Degree;
import com.example.Thuctap.demo.Spring.Service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/degree")
public class DegreeController {
    @Autowired
    private DegreeService degreeService ;
    @PostMapping("/add")
    public Degree addDegree(@RequestBody DegreeDTO degreeDTO ){
        Degree degree = degreeService.addDegree(degreeDTO);
        return degree ;
    }

}
