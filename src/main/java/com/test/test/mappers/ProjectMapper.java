package com.test.test.mappers;

import com.test.test.dtos.EmployeeDto;
import com.test.test.dtos.OrganizationDto;
import com.test.test.dtos.ProjectDetailDto;
import com.test.test.dtos.ProjectDto;
import com.test.test.entities.Employee;
import com.test.test.entities.Organization;
import com.test.test.entities.Project;

import java.util.HashSet;
import java.util.Set;

public class ProjectMapper {
    public static Project dtoToProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setCreated(projectDto.getCreated());
        project.setUpdated(projectDto.getUpdated());
        return project;
    }

    public static ProjectDto projectToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setCreated(project.getCreated());
        projectDto.setUpdated(project.getUpdated());
        projectDto.setEmployeeCount(project.getEmployees().stream().count());
        return projectDto;
    }

    public static Project detailDtoToProject(ProjectDetailDto projectDetailDto) {
        Project project = new Project();
        project.setId(projectDetailDto.getId());
        project.setName(projectDetailDto.getName());
        project.setCreated(projectDetailDto.getCreated());
        project.setUpdated(projectDetailDto.getUpdated());
        Organization organization = OrganizationMapper.dtoToOrganization(projectDetailDto.getOrganization());
        project.setOrganization(organization);
        Set<Employee> employees = new HashSet<>();
        Set<EmployeeDto> employeeDtos = projectDetailDto.getEmployees();
        for (EmployeeDto employeeDto : employeeDtos) {
            Employee employee = EmployeeMapper.dtoToEmployee(employeeDto);
            employees.add(employee);
        }
        project.setEmployees(employees);
        return project;
    }

    public static ProjectDetailDto projectToDetailDto(Project project) {
        ProjectDetailDto projectDetailDto = new ProjectDetailDto();
        projectDetailDto.setId(project.getId());
        projectDetailDto.setName(project.getName());
        projectDetailDto.setCreated(project.getCreated());
        projectDetailDto.setUpdated(project.getUpdated());
        Set<Employee> employees = project.getEmployees();
        Set<EmployeeDto> employeeDtos = new HashSet<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = EmployeeMapper.employeeToDto(employee);
            employeeDtos.add(employeeDto);
        }
        projectDetailDto.setEmployees(employeeDtos);
        OrganizationDto organizationDto = OrganizationMapper.organizationToDto(project.getOrganization());
        projectDetailDto.setOrganization(organizationDto);
        return projectDetailDto;
    }
}
