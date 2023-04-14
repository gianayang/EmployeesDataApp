package com.api.employeesdata.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "employee_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String employee_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "DOB")
    private Date birthday;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private boolean active;

    @Column(name = "age")
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(name = "Employee_Skill", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<>();

    public Employee() {
    }

    public Employee(String first_name, String last_name, Date birthday, String email, boolean active, int age) {
        super();
        this.firstName = first_name;
        this.lastName = last_name;
        this.birthday = birthday;
        this.email = email;
        this.active = active;
        this.age = age;
    }

    public String getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getSkills() {
        List<String> skillList = new ArrayList<String>();
        for (Skill skill : this.skills) {
            skillList.add(skill.getSkill_name());
        }
        return skillList;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setDOB(Date DOB) {
        this.birthday = DOB;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                " employee_id='" + getEmployee_id() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", birthday='" + getBirthday() + "'" +
                ", email='" + getEmail() + "'" +
                ", active='" + isActive() + "'" +
                ", age='" + getAge() + "'" +
                ", skills= " + getSkills() +
                "}";
    }

}
