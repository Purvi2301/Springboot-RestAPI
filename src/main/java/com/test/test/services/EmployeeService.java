package com.test.test.services;

import com.test.test.dtos.DepartmentDto;
import com.test.test.entities.Department;
import com.test.test.entities.Employee;
import com.test.test.exception.EntityNotFoundException;
import com.test.test.repositories.DepartmentRepo;
import com.test.test.repositories.EmployeeRepo;
import com.test.test.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeRepo.findAll().forEach(employees::add);
        return employees;
    }

    public Employee getEmployee(Integer id) {
        if (!employeeRepo.existsById(id))
            throw new EntityNotFoundException("/Employee doesn't exists");
        return employeeRepo.findById(id).get();
    }

    public void createEmployee(Employee employee) {
        employee.getDepartments().forEach(d -> d.setOrganization(employee.getOrganization()));
        employee.getProjects().forEach(p -> p.setOrganization(employee.getOrganization()));
        employeeRepo.save(employee);
    }

    public void updateEmployee(Employee employee, Integer id) {
        if (!employeeRepo.existsById(id))
            throw new EntityNotFoundException("Employee doesn't exists");
        Employee employee1 = employeeRepo.findById(id).get();
        employee1.setName(employee.getName());
        employeeRepo.save(employee1);
    }

    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }

    public void updateDepartmentOfEmployee(DepartmentDto departmentDto, Integer id) {
        if (!employeeRepo.existsById(id))
            throw new EntityNotFoundException("Employee doesn't exist");
        Employee employee = employeeRepo.findById(id).get();
        Department department = departmentRepo.findById(departmentDto.getId()).get();
        department.setName(departmentDto.getName());
        employee.getDepartments().add(department);
        employeeRepo.save(employee);
    }

}
