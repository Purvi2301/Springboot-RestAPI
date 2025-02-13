package com.test.test.mappers;

import com.test.test.dtos.*;
import com.test.test.entities.Department;
import com.test.test.entities.Organization;
import com.test.test.entities.Project;

import java.util.HashSet;
import java.util.Set;

public class OrganizationMapper {
    public static Organization dtoToOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setCreated(organizationDto.getCreated());
        organization.setUpdated(organizationDto.getUpdated());
        return organization;
    }

    public static OrganizationDto organizationToDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setName(organization.getName());
        organizationDto.setCreated(organization.getCreated());
        organizationDto.setUpdated(organization.getUpdated());
        return organizationDto;
    }

    public static Organization detailDtoToOrganization(OrganizationDetailDto organizationDetailDto) {
        Organization organization = new Organization();
        organization.setId(organizationDetailDto.getId());
        organization.setName(organizationDetailDto.getName());
        Set<Project> projects = new HashSet<>();
        Set<ProjectDto> projectDtos = organizationDetailDto.getProjects();
        for (ProjectDto projectDto : projectDtos) {
            Project project = ProjectMapper.dtoToProject(projectDto);
            projects.add(project);
        }
        organization.setProjects(projects);
        Set<Department> departments = new HashSet<>();
        Set<DepartmentDto> departmentDtos = organizationDetailDto.getDepartments();
        for (DepartmentDto departmentDto : departmentDtos) {
            Department department = DepartmentMapper.dtoToDepartment(departmentDto);
            departments.add(department);
        }
        organization.setDepartments(departments);
        return organization;
    }

    public static OrganizationDetailDto OrganizationToDetailDto(Organization organization) {
        OrganizationDetailDto organizationDetailDto = new OrganizationDetailDto();
        organizationDetailDto.setId(organization.getId());
        organizationDetailDto.setName(organization.getName());
        Set<ProjectDto> projectDtos = new HashSet<>();
        Set<Project> projects = organization.getProjects();
        for (Project project : projects) {
            ProjectDto projectDto = ProjectMapper.projectToDto(project);
            projectDtos.add(projectDto);
        }
        organizationDetailDto.setProjects(projectDtos);
        Set<Department> departments = organization.getDepartments();
        Set<DepartmentDto> departmentDtos = new HashSet<>();
        for (Department department : departments) {
            DepartmentDto departmentDto = DepartmentMapper.departmentToDto(department);
            departmentDtos.add(departmentDto);
        }
        organizationDetailDto.setDepartments(departmentDtos);
        return organizationDetailDto;
    }

    public static Organization countDtoToOrganization(OrganizationCountDto organizationCountDto) {
        Organization organization = new Organization();
        organization.setId(organizationCountDto.getId());
        organization.setName(organizationCountDto.getName());
        return organization;
    }

    public static OrganizationCountDto organizationToCountDto(Organization organization) {
        OrganizationCountDto organizationCountDto = new OrganizationCountDto();
        organizationCountDto.setId(organization.getId());
        organizationCountDto.setName(organization.getName());
        organizationCountDto.setDepartmentCount(organization.getDepartments().stream().count());
        organizationCountDto.setProjectCount(organization.getProjects().stream().count());
        organizationCountDto.setEmployeeCount(organization.getEmployees().stream().count());
        return organizationCountDto;
    }
}
