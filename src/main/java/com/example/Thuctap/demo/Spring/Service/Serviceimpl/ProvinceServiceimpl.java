package com.example.Thuctap.demo.Spring.Service.Serviceimpl;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictResDto;
import com.example.Thuctap.demo.Spring.DTO.PDMDto;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceAddto;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceDTO;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceResDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Mapper.DistrictMapper;
import com.example.Thuctap.demo.Spring.Repository.CommuneRepository;
import com.example.Thuctap.demo.Spring.Repository.DistrictRepository;
import com.example.Thuctap.demo.Spring.Repository.ProvinceRepository;
import com.example.Thuctap.demo.Spring.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceimpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository ;
    @Autowired
    private CommuneRepository communeRepository ;
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public void addProvince(ProvinceDTO provinceDTO) {
        Province province1 = new Province();
        province1.setName(provinceDTO.getName());
        Optional<Province> province2 = provinceRepository.findProvinceByName(province1.getName());
        if (province2.isPresent()) {
            throw new DuplicateKeyException("Province already exists");
        }
        provinceRepository.save(province1);
        if (provinceDTO.getDtoList() != null) {
            for (DistrictDTO district : provinceDTO.getDtoList()) {
                District district1 = new District();
                district1.setName(district.getName());
                district1.setProvince(province1);
                districtRepository.save(district1);
            }
        }
    }

    @Override
    public List<ProvinceResDto> getList() {
       List<Province> listProvince = provinceRepository.findAll();
       List<ProvinceResDto> provinceResDtoList = new ArrayList<>();
       for(Province province : listProvince){
           List<District> districtList = districtRepository.findByProvinceId(province.getId());
           List<DistrictDTO> districtDTOS = new ArrayList<>();
           for(District district : districtList){
               districtDTOS.add(districtMapper.toDistrictDTO(district));
           }
           ProvinceResDto provinceResDto = new ProvinceResDto(province.getId(), province.getName(),districtDTOS);
           provinceResDtoList.add(provinceResDto);
       }
           return provinceResDtoList;
    }
    @Override
    public void deleteProvinceById(Long id) {
        Optional<Province> province = provinceRepository.findProvinceById(id);
        List<District> districtList = districtRepository.findByProvinceId(province.get().getId());
        for(District district : districtList){
            districtRepository.deleteById(district.getId());
        }
        provinceRepository.deleteById(province.get().getId());
    }

    @Override
    public ProvinceResDto updateProvince(ProvinceDTO provinceDTO, Long id) {
        Optional<Province> province = provinceRepository.findProvinceById(id);
        province.get().setName(provinceDTO.getName());
        provinceRepository.save(province.get());
        List<District> districtList = districtRepository.findByProvinceId(id);
        List<DistrictDTO> dtoList = new ArrayList<>();
        int i = 0 ;
        for(DistrictDTO districtDTO : provinceDTO.getDtoList()){
            districtList.get(i).setName(districtDTO.getName());
            districtList.get(i).setProvince(province.get());
            districtRepository.save(districtList.get(i));
            dtoList.add(districtMapper.toDistrictDTO(districtList.get(i)));
            i++;
        }
        ProvinceResDto provinceResDto = new ProvinceResDto(id,province.get().getName(),dtoList);
        return provinceResDto;
    }

    @Override
    public Optional<ProvinceResDto> getPronvinceById(Long id) {
         Optional<Province> province = provinceRepository.findProvinceById(id);
         List<District> districtList = districtRepository.findByProvinceId(province.get().getId());
         List<DistrictDTO> dtoList = new ArrayList<>();
         for(District district : districtList){
             dtoList.add(districtMapper.toDistrictDTO(district));
         }
         ProvinceResDto provinceResDto = new ProvinceResDto(province.get().getId(), province.get().getName(),dtoList);
         return Optional.of(provinceResDto);
    }

    @Override
    public Optional<Province> getProvinceByName(String name) {
        return  provinceRepository.findProvinceByName(name);
    }

    @Override
    public void addProvinceContainList(ProvinceAddto provinceAddto) {
        Province province = new Province() ;
        province.setName(provinceAddto.getProvinceName());
        Optional<Province> province1 = provinceRepository.findProvinceByName(province.getName());
        if(province1.isPresent()){
            throw new DuplicateKeyException("Province already exists !");
        }
        else {
            provinceRepository.save(province);
            for(DistrictResDto districtDTO : provinceAddto.getDtoList()){
                District district = new District();
                district.setName(districtDTO.getDistrictName());
                district.setProvince(province);
                districtRepository.save(district);
                for(CommuneDto communeDto : districtDTO.getCommuneDtoList()){
                    Commune commune = new Commune();
                    commune.setName(communeDto.getCommuneName());
                    commune.setDistrict(district);
                    communeRepository.save(commune);
                }
            }
        }
    }


    @Override
    public void deleteProvinceContainList(Long id) {
        Optional<Province> province = provinceRepository.findProvinceById(id);
        List<District> districtList = districtRepository.findByProvinceId(province.get().getId());
        for(District district : districtList){
            List<Commune> communeList = communeRepository.findByDistrictId(district.getId());
            for(Commune commune : communeList){
                communeRepository.deleteById(commune.getId());
            }
            districtRepository.deleteById(district.getId());
        }
        provinceRepository.deleteById(province.get().getId());
    }

    @Override
    public PDMDto getPDM(String provinceName, String districtName) {
        Optional<Province> province = provinceRepository.findProvinceByName(provinceName);
        Optional<District> district = districtRepository.findDistrictByName(districtName);
        PDMDto dto = provinceRepository.findPDM(province.get().getId(), district.get().getId());
        return dto ;


    }

//    @Override
//    public Optional<Province> getProvinceByDistrictName(String name) {
//        return provinceRepository.findByDistrict_Name(name);
//    }


}