package com.example.expensify.repository;

import com.example.expensify.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

  Optional<Employee> findByUsername(String username);
}
