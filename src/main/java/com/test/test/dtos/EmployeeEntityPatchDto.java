package com.test.test.dtos;

import java.util.List;

public class EmployeeEntityPatchDto {
    List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public EmployeeEntityPatchDto() {
    }
}
