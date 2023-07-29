package com.example.Thuctap.demo.Spring.Service;

import com.example.Thuctap.demo.Spring.DTO.PDMDto;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceAddto;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceDTO;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceResDto;
import com.example.Thuctap.demo.Spring.Entity.Province;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProvinceService {
    // thêm tỉnh chứa danh sách huyện
    void addProvince(ProvinceDTO provinceDTO) ;
    List<ProvinceResDto> getList();
    // xóa tỉnh xóa luôn danh sách huyện
    void deleteProvinceById (Long id) ;
    ProvinceResDto updateProvince(ProvinceDTO provinceDTO , Long id );
    Optional<ProvinceResDto> getPronvinceById(Long id) ;
    Optional<Province> getProvinceByName(String name) ;
    // thêm tỉnh chứa danh sách huyện , danh sách xã
    void addProvinceContainList (ProvinceAddto provinceAddto);
    // xóa tỉnh xóa list huyện , list xã
    void deleteProvinceContainList(Long id);
//    Optional<Province> getProvinceByDistrictName (String name);
    PDMDto getPDM(String provinceName , String districtName);
}
