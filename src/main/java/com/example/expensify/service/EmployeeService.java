package com.example.expensify.service;

import com.example.expensify.entity.Employee;
import com.example.expensify.repository.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired private EmployeeRepository employeeRepository;

  public Iterable<Employee> findAll() {
    return this.employeeRepository.findAll();
  }

  public Optional<Employee> findById(Integer id) {
    return this.employeeRepository.findById(id);
  }
}
