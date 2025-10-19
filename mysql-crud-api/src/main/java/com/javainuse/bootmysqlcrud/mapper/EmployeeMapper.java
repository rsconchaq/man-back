package com.javainuse.bootmysqlcrud.mapper;

import com.javainuse.bootmysqlcrud.dto.EmployeeDto;
import com.javainuse.bootmysqlcrud.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(), employee.getName(), employee.getDepartment());
	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getDepartment());
	}

}