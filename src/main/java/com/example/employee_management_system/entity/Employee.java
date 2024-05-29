package com.example.employee_management_system.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            // Create sequence
            sequenceName = "employee_sequence",
            // Increment by 1
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;

    @NotNull
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;
    @NotNull
    private String position;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

//  Constructor
    public Employee() {

    }

    public Employee(Long id,
                    String name,
                    String email,
                    LocalDate dob,
                    String position,
                    Department department,
                    Set<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }

    public Employee(String name,
                    String email,
                    LocalDate dob,
                    String position,
                    Department department,
                    Set<Project> projects) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.position = position;
        this.department = department;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

//    Getter and Setter
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                ", position=" + position +
                ", department=" + department +
                ", projects=" + projects +
                '}';
    }
}
