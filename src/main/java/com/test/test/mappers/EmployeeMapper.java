package com.test.test.mappers;

import com.test.test.dtos.*;
import com.test.test.dtos.EmployeeDetailDto;
import com.test.test.entities.Department;
import com.test.test.entities.Employee;
import com.test.test.entities.Organization;
import com.test.test.entities.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeMapper {
    public static List<Employee> dtoToEmployee(List<EmployeeDto> employees) {
        List<Employee> employees1 = new ArrayList<>();
        for (EmployeeDto employeeDto : employees) {
            Employee employee = new Employee();
            employee.setId(employeeDto.getId());
            employee.setName(employeeDto.getName());
            employee.setUpdated(employeeDto.getUpdated());
            employee.setCreated(employeeDto.getCreated());
            employees1.add(employee);
        }
        return employees1;
    }

    public static List<EmployeeDto> employeeToDto(List<Employee> employees) {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setUpdated(employee.getUpdated());
            employeeDto.setCreated(employee.getCreated());
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }

    public static Employee dtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setCreated(employeeDto.getCreated());
        employee.setUpdated(employeeDto.getUpdated());
        return employee;
    }

    public static EmployeeDto employeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setCreated(employee.getCreated());
        employeeDto.setUpdated(employee.getUpdated());
        return employeeDto;
    }

    public static Employee detailDtoToEmployee(EmployeeDetailDto employeeDetailDto) {
        Employee employee = new Employee();
        employee.setId(employeeDetailDto.getId());
        employee.setName(employeeDetailDto.getName());
        employee.setCreated(employeeDetailDto.getCreated());
        employee.setUpdated(employeeDetailDto.getUpdated());

        Set<Department> departments = new HashSet<>();
        for (DepartmentDto departmentDto : employeeDetailDto.getDepartments()) {
            Department department = DepartmentMapper.dtoToDepartment(departmentDto);
            departments.add(department);
        }
        employee.setDepartments(departments);

        Organization organization = OrganizationMapper.dtoToOrganization(employeeDetailDto.getOrganization());
        employee.setOrganization(organization);

        Set<Project> projects = new HashSet<>();
        for (ProjectDto projectDto : employeeDetailDto.getProjects()) {
            Project project = ProjectMapper.dtoToProject(projectDto);
            projects.add(project);
        }
        employee.setProjects(projects);
        return employee;
    }

    public static EmployeeDetailDto employeeToDetailDto(Employee employee) {
        EmployeeDetailDto employeeDetailDto = new EmployeeDetailDto();
        employeeDetailDto.setName(employee.getName());
        employeeDetailDto.setCreated(employee.getCreated());
        employeeDetailDto.setId(employee.getId());
        employeeDetailDto.setUpdated(employee.getUpdated());

        Set<DepartmentDto> departmentDtos = new HashSet<>();
        for (Department department : employee.getDepartments()) {
            DepartmentDto departmentDto = DepartmentMapper.departmentToDto(department);
            departmentDtos.add(departmentDto);
        }
        employeeDetailDto.setDepartments(departmentDtos);

        OrganizationDto organizationDto = OrganizationMapper.organizationToDto(employee.getOrganization());
        employeeDetailDto.setOrganization(organizationDto);

        Set<ProjectDto> projectDtos = new HashSet<>();
        for (Project project : employee.getProjects()) {
            ProjectDto projectDto = ProjectMapper.projectToDto(project);
            projectDtos.add(projectDto);
        }
        employeeDetailDto.setProjects(projectDtos);

        return employeeDetailDto;
    }

}
