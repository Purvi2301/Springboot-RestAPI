package com.test.test.services;

import com.test.test.dtos.DepartmentDto;
import com.test.test.dtos.OrganizationCountDto;
import com.test.test.dtos.OrganizationDetailDto;
import com.test.test.dtos.OrganizationDto;
import com.test.test.entities.Employee;
import com.test.test.entities.Organization;
import com.test.test.exception.EntityNotFoundException;
import com.test.test.mappers.OrganizationMapper;
import com.test.test.repositories.DepartmentRepo;
import com.test.test.repositories.EmployeeRepo;
import com.test.test.repositories.OrganizationRepo;
import com.test.test.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepo organizationRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ProjectRepo projectRepo;

    public void createOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.dtoToOrganization(organizationDto);
        organizationRepo.save(organization);
    }

    public void updateOrganization(DepartmentDto departmentDto, Integer id) {
        if (!organizationRepo.existsById(id))
            throw new EntityNotFoundException("Organization doesn't exists");
        Organization organization = organizationRepo.findById(id).get();
        organization.setName(departmentDto.getName());
        organizationRepo.save(organization);
    }

    public void deleteOrganization(Integer id) {
        Organization organization = organizationRepo.findById(id).get();
        Set<Employee> employees = organization.getEmployees();
        employees.forEach(employee -> {
            if (employee.getOrganization().equals(organization)) {
                employeeRepo.delete(employee);
            }
        });
        organization.getDepartments().forEach(department -> {
            if (department.getOrganization().equals(organization)) {
                departmentRepo.delete(department);
            }
        });
        organization.getProjects().forEach(project -> {
            if (project.getOrganization().equals(organization)) {
                projectRepo.delete(project);
            }
        });
        organizationRepo.deleteById(id);
    }

    public OrganizationDetailDto getOrganization(Integer id) {
        if (!organizationRepo.existsById(id))
            throw new EntityNotFoundException("Organization doesn't exists");
        Organization organization = organizationRepo.findById(id).get();
        OrganizationDetailDto organizationDetailDto = OrganizationMapper.OrganizationToDetailDto(organization);
        return organizationDetailDto;
    }

    public List<OrganizationCountDto> getAllOrganizations() {
        List<Organization> organizations = organizationRepo.findAll();
        List<OrganizationCountDto> organizationCountDtos = new ArrayList<>();
        for (Organization organization : organizations) {
            OrganizationCountDto organizationCountDto = OrganizationMapper.organizationToCountDto(organization);
            organizationCountDtos.add(organizationCountDto);
        }
        return organizationCountDtos;
    }
}
