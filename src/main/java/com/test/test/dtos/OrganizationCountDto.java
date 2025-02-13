package com.test.test.dtos;

public class OrganizationCountDto {
    private Integer id;
    private String name;
    private Long EmployeeCount;
    private Long DepartmentCount;
    private Long ProjectCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployeeCount() {
        return EmployeeCount;
    }

    public void setEmployeeCount(Long employeeCount) {
        EmployeeCount = employeeCount;
    }

    public Long getDepartmentCount() {
        return DepartmentCount;
    }

    public void setDepartmentCount(Long departmentCount) {
        DepartmentCount = departmentCount;
    }

    public Long getProjectCount() {
        return ProjectCount;
    }

    public void setProjectCount(Long projectCount) {
        ProjectCount = projectCount;
    }

    public OrganizationCountDto() {
    }
}
