package com.test.test.dtos;

import java.util.Set;

public class OrganizationDetailDto {
    private Integer id;
    private String name;
    private Set<DepartmentDto> departments;
    private Set<ProjectDto> projects;

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

    public OrganizationDetailDto() {
    }
}
