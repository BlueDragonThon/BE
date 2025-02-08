
package com.example.template.domain.college.repository;

import com.example.template.domain.college.dto.CollegeResponseDto;
import com.example.template.domain.college.entity.College;
import com.example.template.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    Page<College> findAllByNameContaining(Pageable pageable, String collegeName);
    Page<College> findAllByProgramContaining(Pageable pageable, String programName);
    @Query("select c from College c order by"+
            " (coalesce(c.coordinate.acr,0.0) - :acr)*(coalesce(c.coordinate.acr,0.0) - :acr) +"+
            " (coalesce(c.coordinate.dwn,0.0) - :dwn)*(coalesce(c.coordinate.dwn,0.0) - :dwn)")
    Page<College> searchCollegesByDistance(Pageable pageable, double acr, double dwn);

    String getFavorites = "select new com.example.template.domain.college.dto.CollegeResponseDto(c, "+
            "CASE WHEN (EXISTS (SELECT mc FROM MemberCollege mc WHERE mc.college = c and mc.member=:member)) "+
            "THEN true ELSE false END) from College c ";

    @Query(getFavorites + "where c.name like :collegeName")
    Page<CollegeResponseDto> findAllByNameWithFavorites(@Param("member") Long member, @Param("collegeName") String collegeName, Pageable pageable);

    @Query(getFavorites + "where c.program like :programName")
    Page<CollegeResponseDto> findAllByProgramWithFavorites(Pageable pageable, @Param("member") Member member, @Param("programName") String programName);

    @Query(getFavorites + "order by"+
            " (coalesce(c.coordinate.acr,0.0) - :acr)*(coalesce(c.coordinate.acr,0.0) - :acr) +"+
            " (coalesce(c.coordinate.dwn,0.0) - :dwn)*(coalesce(c.coordinate.dwn,0.0) - :dwn)")
    Page<CollegeResponseDto> searchCollegesByDistanceWithFavorites(Pageable pageable, @Param("member") Member member, double acr, double dwn);
}
