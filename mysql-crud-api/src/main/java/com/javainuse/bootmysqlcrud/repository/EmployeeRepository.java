package com.javainuse.bootmysqlcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javainuse.bootmysqlcrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}