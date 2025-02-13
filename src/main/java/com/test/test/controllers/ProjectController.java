package com.test.test.controllers;

import com.test.test.dtos.EmployeeEntityPatchDto;
import com.test.test.dtos.ProjectDetailDto;
import com.test.test.dtos.ProjectDto;
import com.test.test.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDtos = projectService.getAllProjects();
        return new ResponseEntity<>(projectDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailDto> getProject(@PathVariable Integer id) {
        ProjectDetailDto projectDetailDto = projectService.getProject(id);
        return new ResponseEntity<>(projectDetailDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectDetailDto projectDetailDto) {
        projectService.createProject(projectDetailDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(@RequestBody ProjectDto projectDto, @PathVariable Integer id) {
        projectService.updateProject(projectDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/employees/add")
    public ResponseEntity<Void> addEmployees(@PathVariable Integer id, @RequestBody EmployeeEntityPatchDto employeeEntityPatchDto) {
        projectService.addEmployees(id, employeeEntityPatchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/employees/remove")
    public ResponseEntity<Void> removeEmployees(@PathVariable Integer id, @RequestBody EmployeeEntityPatchDto employeeEntityPatchDto) {
        projectService.removeEmployees(id, employeeEntityPatchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
