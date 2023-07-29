package com.example.Thuctap.demo.Spring.Repository;

import com.example.Thuctap.demo.Spring.Entity.District;
import com.example.Thuctap.demo.Spring.Entity.Province;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findDistrictByName(String name);

    void deleteById(Integer integer);

    Optional<District> findDistrictById(Long id);
    List<District>  findByProvinceId(Long provinceId);
    @Query(value = "select * from district where  district.province_id = :provinceId "+
            "and district.district_name = :districtName",nativeQuery = true)
    District findDistrict( @Param("provinceId") Long provinceId , @Param("districtName") String districtName);



}
