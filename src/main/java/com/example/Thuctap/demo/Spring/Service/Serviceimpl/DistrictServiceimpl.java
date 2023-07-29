package com.example.Thuctap.demo.Spring.Service.Serviceimpl;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictAddDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictCommuAddDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictResDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Mapper.CommuneMapper;
import com.example.Thuctap.demo.Spring.Mapper.DistrictMapper;
import com.example.Thuctap.demo.Spring.Repository.CommuneRepository;
import com.example.Thuctap.demo.Spring.Repository.DistrictRepository;
import com.example.Thuctap.demo.Spring.Repository.ProvinceRepository;
import com.example.Thuctap.demo.Spring.Service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DistrictServiceimpl implements DistrictService {


    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CommuneRepository communeRepository;
    @Autowired
    private CommuneMapper communeMapper;
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<DistrictResDto> getList() {
        List<District> districts = districtRepository.findAll();
        List<DistrictResDto> districtResDtoList = new ArrayList<>();
        for (District district : districts) {
            List<Commune> communeList = communeRepository.findByDistrictId(district.getId());
            List<CommuneDto> dtoList = new ArrayList<>();
            for (Commune commune : communeList) {
                dtoList.add(communeMapper.toCommuneDto(commune));
            }
            DistrictResDto districtResDto = new DistrictResDto(district.getId(), district.getName(), dtoList);
            districtResDtoList.add(districtResDto);
        }
        return districtResDtoList;
    }

    @Override
    public void deleteDistrict(Long id) {
        Optional<District> district = districtRepository.findDistrictById(id);
        List<Commune> communeList = communeRepository.findByDistrictId(district.get().getId());
        for (Commune commune : communeList) {
            communeRepository.deleteById(commune.getId());
        }
        districtRepository.deleteById(district.get().getId());
    }

    @Override
    public DistrictResDto updateDistrict(DistrictResDto districtResDto, Long id) {
        Optional<District> district = districtRepository.findDistrictById(id);
        district.get().setName(districtResDto.getDistrictName());
        List<Commune> communeList = communeRepository.findByDistrictId(id);
        List<CommuneDto> communeDtos = new ArrayList<>();
        int i = 0;
        for(CommuneDto communeDto : districtResDto.getCommuneDtoList()){
            communeList.get(i).setName(communeDto.getCommuneName());
            communeList.get(i).setDistrict(district.get());
            communeRepository.save(communeList.get(i));
            communeDtos.add(communeMapper.toCommuneDto(communeList.get(i)));
            i++;
        }
         DistrictResDto districtResDto1 = new DistrictResDto(district.get().getId(),district.get().getName(),communeDtos);
        return districtResDto1 ;
    }


    @Override
    public List<DistrictDTO> getDistrictsById(Long id) throws Exception {
        Optional<Province> province = provinceRepository.findProvinceById(id);
        List<District> districts = districtRepository.findByProvinceId(province.get().getId());
        List<DistrictDTO> dtoList = new ArrayList<>();
        for (District district : districts) {
            dtoList.add(districtMapper.toDistrictDTO(district));
        }
        return dtoList;
    }

    @Override
    public DistrictResDto addDistrictAndListDistrict(DistrictCommuAddDto districtCommuAddDto) {
        DistrictResDto districtResDto = new DistrictResDto();
        District district = new District();
        district.setName(districtCommuAddDto.getDistrictName());
        Optional<Province> province = provinceRepository.findProvinceById(districtCommuAddDto.getProvinceID());
        districtResDto.setDistrictName(districtCommuAddDto.getDistrictName());
        district.setProvince(province.get());
        Optional<District> district1 = districtRepository.findDistrictByName(district.getName());
        if (district1.isPresent()) {
            throw new DuplicateKeyException("District already exists !");
        } else {
            districtRepository.save(district);
            districtResDto.setDistrictID(district.getId());

        }
        List<CommuneDto> dtoList = new ArrayList<>();
        if (districtCommuAddDto.getCommuneDtos() != null) {
            for (CommuneDto communeDto : districtCommuAddDto.getCommuneDtos()) {
                Commune commune = new Commune();
                commune.setName(communeDto.getCommuneName());
                commune.setDistrict(district);
                communeRepository.save(commune);
                dtoList.add(communeMapper.toCommuneDto(commune));
            }
            districtResDto.setCommuneDtoList(dtoList);
        }

        return districtResDto;

    }

    @Override
    public District getDistrict(String provinceName, String districtName) {
        Optional<Province> province = provinceRepository.findProvinceByName(provinceName);
        Optional<District> district = districtRepository.findDistrictByName(districtName);
        District district1 = districtRepository.findDistrict(province.get().getId(),district.get().getName());
        return district1;
    }


    @Override
    public Optional<District> getDistrictById(Long id) {
        return districtRepository.findDistrictById(id);
    }

    @Override
    public District addDistrict(DistrictAddDto districtAddDto) {
        Optional<Province> province = provinceRepository.findProvinceById(districtAddDto.getProvinceID());
        District district = new District();
        district.setName(districtAddDto.getDistrictName());
        district.setProvince(province.get());
        districtRepository.save(district);
        return district;
    }
}




