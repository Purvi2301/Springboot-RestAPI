package com.test.test.dtos;

import java.sql.Timestamp;
import java.util.Set;

public class EmployeeDetailDto {
    private Integer id;
    private String name;
    private Timestamp created;
    private Timestamp updated;
    private Set<DepartmentDto> departments;
    private Set<ProjectDto> projects;
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

    public Set<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDto> departments) {
        this.departments = departments;
    }

    public Set<ProjectDto> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDto> projects) {
        this.projects = projects;
    }

    public OrganizationDto getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDto organization) {
        this.organization = organization;
    }
}
