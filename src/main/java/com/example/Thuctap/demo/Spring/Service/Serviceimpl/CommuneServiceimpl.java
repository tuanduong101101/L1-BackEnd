package com.example.Thuctap.demo.Spring.Service.Serviceimpl;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneAddDto;
import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneResDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Mapper.CommuneMapper;
import com.example.Thuctap.demo.Spring.Mapper.DistrictMapper;
import com.example.Thuctap.demo.Spring.Repository.CommuneRepository;
import com.example.Thuctap.demo.Spring.Repository.DistrictRepository;
import com.example.Thuctap.demo.Spring.Repository.ProvinceRepository;
import com.example.Thuctap.demo.Spring.Service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommuneServiceimpl implements CommuneService {
    @Autowired
    private DistrictRepository districtRepository ;
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private CommuneMapper communeMapper;
    @Override
    public CommuneResDto addCommuneByName(CommuneAddDto communeAddDto) {
        Optional<District> district = districtRepository.findDistrictByName(communeAddDto.getDistrictName());
        Commune commune = new Commune();
        commune.setName(communeAddDto.getCommuneName());
        commune.setDistrict(district.get());
        communeRepository.save(commune);
        CommuneResDto communeResDto = new CommuneResDto();
        communeResDto.setCommuneId(commune.getId());
        communeResDto.setCommuneName(communeAddDto.getCommuneName());
        communeResDto.setDistrictDTO(districtMapper.toDistrictDTO(district.get()));
        return communeResDto;
    }

    @Override
    public List<CommuneDto> getListCommuneByDistrictId(Long id) {
        Optional<District> district = districtRepository.findDistrictById(id);
        List<Commune> communeList = communeRepository.findByDistrictId(district.get().getId());
        List<CommuneDto> communeDtoList = new ArrayList<>();
        for(Commune commune : communeList){
            communeDtoList.add(communeMapper.toCommuneDto(commune));
        }
        return communeDtoList;
    }

    @Override
    public Commune getCommune(String districtName, String communeName) {
        Optional<District> district = districtRepository.findDistrictByName(districtName);
        Optional<Commune> commune = communeRepository.findByName(communeName);
        Commune commune1 = communeRepository.findCommune(district.get().getId(),commune.get().getName());
        return commune1;
    }


}
