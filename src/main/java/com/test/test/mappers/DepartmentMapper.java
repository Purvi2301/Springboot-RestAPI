package com.test.test.mappers;

import com.test.test.dtos.*;
import com.test.test.entities.Department;
import com.test.test.entities.Employee;
import com.test.test.entities.Organization;

import java.util.HashSet;
import java.util.Set;

public class DepartmentMapper {
    public static Department dtoToDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setCreated(departmentDto.getCreated());
        department.setUpdated(departmentDto.getUpdated());
        return department;
    }

    public static DepartmentDto departmentToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCreated(department.getCreated());
        departmentDto.setUpdated(department.getUpdated());
        return departmentDto;
    }

    public static Department detailDtoToDepartment(DepartmentDetailDto departmentDetailDto) {
        Department department = new Department();
        department.setId(departmentDetailDto.getId());
        department.setName(departmentDetailDto.getName());
        department.setCreated(departmentDetailDto.getCreated());
        department.setUpdated(departmentDetailDto.getUpdated());
        Set<Employee> employees = new HashSet<>();
        Set<EmployeeDto> employeeDtos = departmentDetailDto.getEmployees();
        for (EmployeeDto employeeDto : employeeDtos) {
            Employee employee = EmployeeMapper.dtoToEmployee(employeeDto);
            employees.add(employee);
        }
        department.setEmployees(employees);
        Organization organization = OrganizationMapper.dtoToOrganization(departmentDetailDto.getOrganization());
        department.setOrganization(organization);
        return department;
    }

    public static DepartmentDetailDto departmentToDetailDto(Department department) {
        DepartmentDetailDto departmentDetailDto = new DepartmentDetailDto();
        departmentDetailDto.setId(department.getId());
        departmentDetailDto.setName(department.getName());
        departmentDetailDto.setCreated(department.getCreated());
        departmentDetailDto.setUpdated(department.getUpdated());
        OrganizationDto organizationDto = OrganizationMapper.organizationToDto(department.getOrganization());
        departmentDetailDto.setOrganization(organizationDto);
        Set<Employee> employees = department.getEmployees();
        Set<EmployeeDto> employeeDtos = new HashSet<>();
        for (Employee employee : employees) {
            EmployeeDto employeeDto = EmployeeMapper.employeeToDto(employee);
            employeeDtos.add(employeeDto);
        }
        departmentDetailDto.setEmployees(employeeDtos);
        return departmentDetailDto;
    }

    public static Department countDtoToDepartment(DepartmentCountDto departmentCountDto) {
        Department department = new Department();
        department.setId(departmentCountDto.getId());
        department.setName(departmentCountDto.getName());
        return department;
    }

    public static DepartmentCountDto departmentToCountDto(Department department) {
        DepartmentCountDto departmentCountDto = new DepartmentCountDto();
        departmentCountDto.setId(department.getId());
        departmentCountDto.setName(department.getName());
        departmentCountDto.setEmployeeCount(department.getEmployees().stream().count());
        return departmentCountDto;
    }
}
