package com.example.Thuctap.demo.Spring.Mapper;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import org.springframework.stereotype.Component;

@Component
public class CommuneMapper {
    public CommuneDto toCommuneDto(Commune commune){
        CommuneDto communeDto = new CommuneDto();
        communeDto.setCommuneID(commune.getId());
        communeDto.setCommuneName(commune.getName());
        return communeDto ;
    }
}
