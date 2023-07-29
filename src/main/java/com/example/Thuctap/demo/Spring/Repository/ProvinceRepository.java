package com.example.Thuctap.demo.Spring.Repository;

import com.example.Thuctap.demo.Spring.DTO.PDMDto;
import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Optional<Province> findProvinceByName(String name);
    void deleteById(Integer integer);
    Optional<Province> findProvinceById(Long id);
    Optional<Province> findByName(String provinceName);
//    Optional<Province> findByDistrict(Optional<District> district);
    @Query(value = "select province.province_name , district.district_name , commune.commune_name from ((district " +
            "inner join province on district.province_id =:provinceId)" +
            "inner join commune on commune.district_id=:districtId )",nativeQuery = true)
    PDMDto findPDM (@Param("provinceId") Long provinceId ,@Param("districtId") Long districtId);
}
