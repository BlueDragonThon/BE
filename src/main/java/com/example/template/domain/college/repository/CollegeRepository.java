package com.example.template.domain.college.repository;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.college.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
}
