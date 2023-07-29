package com.example.Thuctap.demo.Spring.Controller;

import com.example.Thuctap.demo.Spring.DTO.District.DistrictAddDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictCommuAddDto;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictDTO;
import com.example.Thuctap.demo.Spring.DTO.District.DistrictResDto;
import com.example.Thuctap.demo.Spring.DTO.Employee.EmployeeDTO;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Repository.DistrictRepository;
import com.example.Thuctap.demo.Spring.Service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private DistrictRepository districtRepository ;

    // lay ra danh sach quan chua toan bo xa thuoc quan
    @GetMapping("/list")
    public List<DistrictResDto> list() {
        List<DistrictResDto> result = districtService.getList();
        return result;

    }

    // xóa huyện xóa luôn toàn bộ xã thuộc huyện đó
    @DeleteMapping("/{id}")
    public void deleteDistrict(@PathVariable("id") Long id) {
        districtService.deleteDistrict(id);
        ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDistrict(@RequestBody DistrictResDto districtResDto, @PathVariable("id") Long id) {
        DistrictResDto district1 = districtService.updateDistrict(districtResDto, id);
        return ResponseEntity.ok(district1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDistrictById(@PathVariable("id") Long id) {
        Optional<District> district = districtService.getDistrictById(id);
        return ResponseEntity.ok(district);
    }

    // them huyen xac dinh luon thuoc tinh nao
    @PostMapping("/addByName")
    public ResponseEntity<?> addDistrictByName(@RequestBody DistrictAddDto districtAddDto) {
        District district1 = districtService.addDistrict(districtAddDto);
        return ResponseEntity.ok(district1);
    }

    // lay toan bo huyen dua vao id tinh
    @GetMapping("/listDistrict/{id}")
    public ResponseEntity<?> getListDistrict(@PathVariable("id") Long id) throws Exception {
        List<DistrictDTO> districts = districtService.getDistrictsById(id);
        return ResponseEntity.ok(districts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDistrict(@RequestBody DistrictCommuAddDto districtCommuAddDto) {
        DistrictResDto districtResDto = districtService.addDistrictAndListDistrict(districtCommuAddDto);
        return ResponseEntity.ok(districtResDto);
    }

    @GetMapping("/getDistrict/{provinceName}/{districtName}")
    public ResponseEntity<?> getDistrict(@PathVariable("provinceName") String provinceName,
                                         @PathVariable("districtName") String districtName ) {
       District district = districtService.getDistrict(provinceName,districtName);
        return ResponseEntity.ok(district);
    }
}
