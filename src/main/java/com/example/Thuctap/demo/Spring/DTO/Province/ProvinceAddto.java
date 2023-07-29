package com.example.Thuctap.demo.Spring.DTO.Province;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// class thêm tỉnh , danh sách huyện , danh sách xã
public class ProvinceAddto {
    private String provinceName;
    private List<DistrictResDto> dtoList ;
}
