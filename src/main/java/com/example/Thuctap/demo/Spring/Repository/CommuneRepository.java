package com.example.Thuctap.demo.Spring.Repository;

import com.example.Thuctap.demo.Spring.Entity.Commune;
import com.example.Thuctap.demo.Spring.Entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommuneRepository extends JpaRepository<Commune,Long> {

    List<Commune> findByDistrictId(Long id);

    Optional<Commune> findByName(String commune);
    @Query(value = "select * from commune where  commune.district_id = :districtId "+
            "and commune.commune_name = :communeName",nativeQuery = true)
    Commune findCommune(@Param("districtId") Long districtId , @Param("communeName") String communeName);
}
