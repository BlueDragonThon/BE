
package com.example.template.domain.college.repository;

import com.example.template.domain.college.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    Page<College> findAllByNameContaining(Pageable pageable, String collegeName);
    Page<College> findAllByProgramContaining(Pageable pageable, String programName);
    @Query("select c from College c order by"+
            " (coalesce(c.coordinate.acr,0.0) - :acr)*(coalesce(c.coordinate.acr,0.0) - :acr) +"+
            " (coalesce(c.coordinate.dwn,0.0) - :dwn)*(coalesce(c.coordinate.dwn,0.0) - :dwn)")
    Page<College> searchCollegesByDistance(Pageable pageable, double acr, double dwn);
}
