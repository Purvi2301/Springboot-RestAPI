package com.test.test.dtos;

import java.sql.Timestamp;
import java.util.Set;

public class ProjectDetailDto {
    private Integer id;
    private String name;
    private Timestamp created;
    private Timestamp updated;
    private Set<EmployeeDto> employees;
    private OrganizationDto organization;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Set<EmployeeDto> getEmployees() {
        return employees;
    }

    public OrganizationDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDto organization) {
        this.organization = organization;
    }

    public void setEmployees(Set<EmployeeDto> employees) {
        this.employees = employees;
    }

    public ProjectDetailDto() {
    }
}
