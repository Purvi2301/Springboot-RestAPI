package com.test.test.services;

import com.test.test.dtos.DepartmentCountDto;
import com.test.test.dtos.EmployeeEntityPatchDto;
import com.test.test.entities.Department;
import com.test.test.entities.Employee;
import com.test.test.exception.EntityNotFoundException;
import com.test.test.mappers.DepartmentMapper;
import com.test.test.repositories.DepartmentRepo;
import com.test.test.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    public void createDepartment(Department department) {
        Set<Department> departments = Set.of(department);
        for (Employee employee : department.getEmployees()) {
            employee.setDepartments(departments);
            employee.setOrganization(department.getOrganization());
        }
        departmentRepo.save(department);
    }

    public void deleteDepartment(Integer id) {
        Department department = departmentRepo.findById(id).get();
        Set<Employee> employees = departmentRepo.findById(id).get().getEmployees();
        employees.forEach(employee -> {
            if (employee.getDepartments().contains(department)) {
                employee.getDepartments().remove(department);
            }
        });
        departmentRepo.deleteById(id);
    }

    public void updateDepartment(Department department, Integer id) {
        if (!departmentRepo.existsById(id))
            throw new EntityNotFoundException("Department doesn't exists");
        Department department1 = departmentRepo.findById(id).get();
        department1.setName(department.getName());
        departmentRepo.save(department1);
    }

    public Department getDepartment(Integer id) {
        if (!departmentRepo.existsById(id))
            throw new EntityNotFoundException("Department doesn't exists");
        return departmentRepo.findById(id).get();
    }

    public List<DepartmentCountDto> getDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentCountDto> departmentCountDtos = new ArrayList<>();
        for (Department department : departments) {
            DepartmentCountDto departmentCountDto = DepartmentMapper.departmentToCountDto(department);
            departmentCountDtos.add(departmentCountDto);
        }
        return departmentCountDtos;
    }

    public void addEmployees(Integer id, EmployeeEntityPatchDto employeeEntityPatchDto) {
        if (!departmentRepo.existsById(id))
            throw new EntityNotFoundException("Department doesn't exists");
        Department department = departmentRepo.findById(id).get();
        Set<Employee> employees = new HashSet<>();
        List<Employee> employeeList = employeeRepo.findAllById(employeeEntityPatchDto.getIds());
        for (Employee employee : employeeList) {
            if (!employee.getDepartments().contains(department)) {
                employee.getDepartments().add(department);
                employeeRepo.save(employee);
            }
        }
        department.setEmployees(employees);
        departmentRepo.save(department);
    }

    public void removeEmployees(Integer id, EmployeeEntityPatchDto employeeEntityPatchDto) {
        if (!departmentRepo.existsById(id))
            throw new EntityNotFoundException("Department doesn't exists");
        Department department = departmentRepo.findById(id).get();
        Set<Employee> employees = new HashSet<>();
        List<Employee> employeeList = employeeRepo.findAllById(employeeEntityPatchDto.getIds());
        for (Employee employee : employeeList) {
            if (employee.getDepartments().contains(department)) {
                employee.getDepartments().remove(department);
                employeeRepo.save(employee);
            }
        }
        department.setEmployees(employees);
        departmentRepo.save(department);
    }

}
