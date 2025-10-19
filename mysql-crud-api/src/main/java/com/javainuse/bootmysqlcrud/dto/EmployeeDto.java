package com.javainuse.bootmysqlcrud.dto;

public class EmployeeDto {

    private Long id;

    private String name;

    private String department;

    public EmployeeDto(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public EmployeeDto() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}