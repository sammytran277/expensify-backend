package com.example.expensify.service;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.ExpensifyUser;
import com.example.expensify.entity.Reviewer;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ReviewerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExpensifyUserService implements UserDetailsService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private ReviewerRepository reviewerRepository;

  @Override
  public ExpensifyUser loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Employee> employee = employeeRepository.findByUsername(username);
    if (employee.isPresent()) {
      return employee.get();
    }
    Optional<Reviewer> reviewer = reviewerRepository.findByUsername(username);
    if (reviewer.isPresent()) {
      return reviewer.get();
    }
    throw new UsernameNotFoundException(username);
  }
}
