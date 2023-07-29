package com.example.Thuctap.demo.Spring.Controller;

import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneAddDto;
import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneDto;
import com.example.Thuctap.demo.Spring.DTO.Commune.CommuneResDto;
import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Service.CommuneService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commune")
public class CommuneController {
    @Autowired
    private CommuneService communeService ;
    // thêm xã xác định luôn thuộc huyện nào
    @PostMapping("/addByName")
    public ResponseEntity<?> addCommuneByDistrictName(@RequestBody CommuneAddDto communeAddDto){
        CommuneResDto commune = communeService.addCommuneByName(communeAddDto);
        return ResponseEntity.ok(commune);
    }
    //tìm kiếm toàn bộ xã dựa theo id của huyện
    @GetMapping("/listByDistrict/{id}")
    public ResponseEntity<?> getListCommuneByDistrictId(@PathVariable("id") Long id){
        List<CommuneDto> communeList = communeService.getListCommuneByDistrictId(id);
        return ResponseEntity.ok(communeList);
    }
    @GetMapping("/getDistrict/{districtName}/{communeName}")
    public ResponseEntity<?> getDistrict(@PathVariable("districtName") String districtName,
                                         @PathVariable("communeName") String communeName ) {
        Commune commune = communeService.getCommune(districtName,communeName);
        return ResponseEntity.ok(commune);
    }

}
