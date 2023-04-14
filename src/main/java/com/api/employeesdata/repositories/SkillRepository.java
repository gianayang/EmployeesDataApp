package com.api.employeesdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.employeesdata.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, String>{
}
