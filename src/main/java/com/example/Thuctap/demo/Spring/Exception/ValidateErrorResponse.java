package com.example.Thuctap.demo.Spring.Exception;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ValidateErrorResponse {
    private Map<String,List<Map<Integer,String>>> violation =new HashMap<>();
}
@AllArgsConstructor
@NoArgsConstructor
@Data
 class ValidateError{
    private List<Map<Integer,String>> violation = new ArrayList<>() ;
}