package com.example.Thuctap.demo.Spring.Service;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictAddDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictCommuAddDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictResDto;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {

     List<DistrictResDto> getList();
     void deleteDistrict (Long id) ;
     DistrictResDto updateDistrict(DistrictResDto districtResDto , Long id );
     Optional<District> getDistrictById(Long id);
     District addDistrict(DistrictAddDto districtAddDto) ;
     List<DistrictDTO> getDistrictsById(Long id) throws Exception;
     DistrictResDto addDistrictAndListDistrict(DistrictCommuAddDto districtCommuAddDto);
     District getDistrict (String provinceName , String districtName );


}
