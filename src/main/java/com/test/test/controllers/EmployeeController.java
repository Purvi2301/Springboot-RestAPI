package com.test.test.controllers;

import com.test.test.dtos.DepartmentDto;
import com.test.test.dtos.EmployeeDetailDto;
import com.test.test.dtos.EmployeeDto;
import com.test.test.entities.Employee;
import com.test.test.mappers.EmployeeMapper;
import com.test.test.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtos = EmployeeMapper.employeeToDto(employees);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailDto> getEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployee(id);
        EmployeeDetailDto employeeDetailDto = EmployeeMapper.employeeToDetailDto(employee);
        return new ResponseEntity<>(employeeDetailDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDetailDto employeeDetailDto) {
        Employee employee = EmployeeMapper.detailDtoToEmployee(employeeDetailDto);
        employeeService.createEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Integer id) {
        Employee employee = EmployeeMapper.dtoToEmployee(employeeDto);
        employeeService.updateEmployee(employee, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("{id}/department")
    public ResponseEntity<Void> updateDepartmentOfEmployee(@RequestBody DepartmentDto departmentDto, @PathVariable Integer id) {
        employeeService.updateDepartmentOfEmployee(departmentDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
