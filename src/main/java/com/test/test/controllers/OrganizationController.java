package com.test.test.controllers;

import com.test.test.dtos.DepartmentDto;
import com.test.test.dtos.OrganizationCountDto;
import com.test.test.dtos.OrganizationDetailDto;
import com.test.test.dtos.OrganizationDto;
import com.test.test.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<List<OrganizationCountDto>> getAllOrganizations() {
        List<OrganizationCountDto> organizationCountDto = organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizationCountDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDetailDto> getOrganization(@PathVariable Integer id) {
        OrganizationDetailDto organizationDetailDto = organizationService.getOrganization(id);
        return new ResponseEntity<>(organizationDetailDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createOrganization(@RequestBody OrganizationDto organizationDto) {
        organizationService.createOrganization(organizationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrganization(@RequestBody DepartmentDto departmentDto, @PathVariable Integer id) {
        organizationService.updateOrganization(departmentDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Integer id) {
        organizationService.deleteOrganization(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
