package com.example.Thuctap.demo.Spring.Service;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneAddDto;
import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneResDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommuneService {
    CommuneResDto addCommuneByName(CommuneAddDto communeAddDto);
    List<CommuneDto> getListCommuneByDistrictId(Long id);
    Commune getCommune(String districtName, String communeName);
}
