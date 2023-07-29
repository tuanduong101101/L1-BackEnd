package com.example.Thuctap.demo.Spring.Controller;

import com.example.Thuctap.demo.Spring.DTO.PDMDto;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceAddto;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceDTO;
import com.example.Thuctap.demo.Spring.DTO.Province.ProvinceResDto;
import com.example.Thuctap.demo.Spring.Entity.Province;
import com.example.Thuctap.demo.Spring.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ParameterMetaData;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService ;
    @GetMapping("/list")
    public List<ProvinceResDto> list (){
        List<ProvinceResDto> provinceList = provinceService.getList() ;
        return  provinceList;
    }
    // thêm tỉnh và danh sách huyện cùng lúc
    @PostMapping("/add")
    public void addProvince(@RequestBody ProvinceDTO provinceDTO) throws Exception {
         provinceService.addProvince(provinceDTO);

      }
      @DeleteMapping("/{id}")
    public void deleteProvince(@PathVariable("id") Long id){
        provinceService.deleteProvinceById(id);
      }

      @GetMapping("/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable("id") Long id){
         Optional<ProvinceResDto> province = provinceService.getPronvinceById(id);
         return ResponseEntity.ok(province);
      }
      @GetMapping("/findbyname")
    public Optional<Province> getProvinceByName(@RequestParam String name){
        return provinceService.getProvinceByName(name);
      }
      // sửa tỉnh và huyện cùng lúc
      @PutMapping("/update/{id}")
    public ProvinceResDto updateProvince(@RequestBody ProvinceDTO provinceDTO ,@PathVariable("id") Long id){
        ProvinceResDto provinceResDto = provinceService.updateProvince(provinceDTO,id);
        return provinceResDto;
      }
      @PostMapping("/addProvince")
    public void addProvinceContainList(@RequestBody ProvinceAddto provinceAddto){
        provinceService.addProvinceContainList(provinceAddto);
      }
      @DeleteMapping("/delete/{id}")
    public void deleteProvinceContainList(@PathVariable("id") Long id){
        provinceService.deleteProvinceContainList(id);
      }
//      @GetMapping("/getProvince")
//    public ResponseEntity<?> getProvinceByDistrict(@RequestParam String name){
//        Optional<Province> province = provinceService.getProvinceByDistrictName(name);
//        return ResponseEntity.ok(province);
//      }
    @GetMapping("/getDto/{provinceName}/{districtName}")
    public PDMDto getPMD (@PathVariable("provinceName") String provinceName , @PathVariable("districtName") String districtName){
        PDMDto dto = provinceService.getPDM(provinceName,districtName);
        return dto ;
    }
}
