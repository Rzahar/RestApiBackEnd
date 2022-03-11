package com.springboot.exo.springbootbackend.repository;

import com.springboot.exo.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
