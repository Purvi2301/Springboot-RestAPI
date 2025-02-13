package com.test.test.controllers;

import com.test.test.dtos.*;
import com.test.test.entities.Department;
import com.test.test.mappers.DepartmentMapper;
import com.test.test.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentCountDto>> getDepartments() {
        List<DepartmentCountDto> departments = departmentService.getDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDetailDto> getDepartment(@PathVariable Integer id) {
        Department department = departmentService.getDepartment(id);
        DepartmentDetailDto departmentDetailDto = DepartmentMapper.departmentToDetailDto(department);
        return new ResponseEntity<>(departmentDetailDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentDetailDto departmentDetailDto) {
        Department department = DepartmentMapper.detailDtoToDepartment(departmentDetailDto);
        departmentService.createDepartment(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@RequestBody DepartmentDto departmentDto, @PathVariable Integer id) {
        Department department = DepartmentMapper.dtoToDepartment(departmentDto);
        departmentService.updateDepartment(department, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/employees/add")
    public ResponseEntity<Void> addEmployees(@PathVariable Integer id, @RequestBody EmployeeEntityPatchDto employeeEntityPatchDto) {
        departmentService.addEmployees(id, employeeEntityPatchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/employees/remove")
    public ResponseEntity<Void> removeEmployees(@PathVariable Integer id, @RequestBody EmployeeEntityPatchDto employeeEntityPatchDto) {
        departmentService.removeEmployees(id, employeeEntityPatchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
