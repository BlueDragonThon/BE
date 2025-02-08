package com.example.template.domain.college.repository;

import com.example.template.domain.college.entity.College;
import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberCollegeRepository extends JpaRepository<MemberCollege, Long> {
    @Query("select mc.college FROM MemberCollege mc where mc.member=:member order by mc.id asc")
    Page<College> findByFavorites(Pageable pageable, Member member);

    Optional<MemberCollege> findByMemberAndCollege(Member member, College college);
}