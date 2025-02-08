package com.example.template.domain.college.repository;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    List<College> findAllByNameContaining(String collegeName);
    List<College> findAllByProgramContaining(String programName);
}
