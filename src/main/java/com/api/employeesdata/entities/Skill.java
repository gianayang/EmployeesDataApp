package com.api.employeesdata.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Skill {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "skill_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String skill_id;

    @Column(name = "skill_name")
    private String skill_name;

    @Column(name = "skill_description")
    private String skill_description;


    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {
        CascadeType.MERGE,
        CascadeType.DETACH,
        CascadeType.REFRESH
    }, mappedBy="skills")
    transient private List<Employee> employees = new ArrayList<>();

    public Skill() {
    }

    public Skill(String skill_name, String skill_description) {
        this.skill_name = skill_name;
        this.skill_description = skill_description;
    }

    public String getSkill_id() {
        return this.skill_id;
    }

    public void setSkill_id(String skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_name() {
        return this.skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkill_description() {
        return this.skill_description;
    }

    public void setSkill_description(String skill_description) {
        this.skill_description = skill_description;
    }

    @JsonIgnoreProperties(value = "skills")
    public List<String> getEmployees() {
        List<String> empList = new ArrayList<String>();
        for (Employee emp: this.employees) {
            empList.add(emp.getEmployee_id());
        }
        return empList;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }


    @Override
    public String toString() {
        return "{" +
            " skill_id='" + getSkill_id() + "'" +
            ", skill_name='" + getSkill_name() + "'" +
            ", skill_description='" + getSkill_description() + "'" +
            "}";
    }


}
