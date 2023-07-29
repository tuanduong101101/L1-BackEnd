package com.example.Thuctap.demo.Spring.Repository;

import com.example.Thuctap.demo.Spring.Entity.Degree;
import com.example.Thuctap.demo.Spring.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree,Long> {
    List<Degree> findByEmployeeId(Long id);

    Optional<Degree> findByProvinceIdAndEmployeeIdAndName(Long provinceId, Long employeeId,String degreeName);
    @Query(value = "select * from degree where degree.name = :name group by degree.end_day having degree.end_day > :localDate"
            , nativeQuery = true)
    List<Degree>  getListDegreeValidate (@Param("name")String name , @Param("localDate") LocalDate localDate);
}
