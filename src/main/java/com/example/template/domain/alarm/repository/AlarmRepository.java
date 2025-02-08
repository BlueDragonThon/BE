package com.example.template.domain.alarm.repository;

import com.example.template.domain.alarm.entity.Alarm;
import com.example.template.domain.mapping.MemberCollege;
import com.example.template.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByMemberCollege_Member(@Param("member") Member member);

    void deleteByMemberCollege(MemberCollege memberCollege);
}
