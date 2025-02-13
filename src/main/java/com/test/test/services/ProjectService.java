package com.test.test.services;

import com.test.test.dtos.EmployeeEntityPatchDto;
import com.test.test.dtos.ProjectDetailDto;
import com.test.test.dtos.ProjectDto;
import com.test.test.entities.Employee;
import com.test.test.entities.Project;
import com.test.test.exception.EntityNotFoundException;
import com.test.test.mappers.ProjectMapper;
import com.test.test.repositories.EmployeeRepo;
import com.test.test.repositories.OrganizationRepo;
import com.test.test.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private OrganizationRepo organizationRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public void createProject(ProjectDetailDto projectDetailDto) {
        Project project = ProjectMapper.detailDtoToProject(projectDetailDto);
        Set<Project> projects = Set.of(project);
        for (Employee employee : project.getEmployees()) {
            employee.setProjects(projects);
            employee.setOrganization(project.getOrganization());
        }
        projectRepo.save(project);
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (Project project : projects) {
            ProjectDto projectDto = ProjectMapper.projectToDto(project);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    public ProjectDetailDto getProject(Integer id) {
        if (!projectRepo.existsById(id))
            throw new EntityNotFoundException("Project doesn't exists");
        Project project = projectRepo.findById(id).get();
        ProjectDetailDto projectDetailDto = ProjectMapper.projectToDetailDto(project);
        return projectDetailDto;
    }

    public void updateProject(ProjectDto projectDto, Integer id) {
        if (!projectRepo.existsById(id))
            throw new EntityNotFoundException("Project doesn't exists");
        Project project = projectRepo.findById(id).get();
        project.setName(projectDto.getName());
        projectRepo.save(project);
    }

    public void deleteProject(Integer id) {
        Project project = projectRepo.findById(id).get();
        Set<Employee> employees = projectRepo.findById(id).get().getEmployees();
        employees.forEach(employee -> {
            if (employee.getProjects().contains(project)) {
                employee.getProjects().remove(project);
            }
        });
        projectRepo.deleteById(id);
    }

    public void addEmployees(Integer id, EmployeeEntityPatchDto employeeEntityPatchDto) {
        if (!projectRepo.existsById(id))
            throw new EntityNotFoundException("Project doesn't exists");
        Project project = projectRepo.findById(id).get();
        Set<Employee> employees = new HashSet<>();
        List<Employee> employeeList = employeeRepo.findAllById(employeeEntityPatchDto.getIds());
        for (Employee employee : employeeList) {
            if (!employee.getProjects().contains(project)) {
                employee.getProjects().add(project);
                employeeRepo.save(employee);
            }
        }
        project.setEmployees(employees);
        projectRepo.save(project);
    }

    public void removeEmployees(Integer id, EmployeeEntityPatchDto employeeEntityPatchDto) {
        if (!projectRepo.existsById(id))
            throw new EntityNotFoundException("Project doesn't exists");
        Project project = projectRepo.findById(id).get();
        Set<Employee> employees = new HashSet<>();
        List<Employee> employeeList = employeeRepo.findAllById(employeeEntityPatchDto.getIds());
        for (Employee employee : employeeList) {
            if (employee.getProjects().contains(project)) {
                employee.getProjects().remove(project);
                employeeRepo.save(employee);
            }
        }
        project.setEmployees(employees);
        projectRepo.save(project);
    }
}

