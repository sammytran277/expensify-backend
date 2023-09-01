package com.example.expensify.service;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ExpenseRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private ExpenseRepository expenseRepository;

  public Expense addOne(Employee employee, Expense expense) {
    Expense expenseWithId = this.expenseRepository.save(expense);
    employee.getExpenses().add(expense);
    employeeRepository.save(employee);
    return expenseWithId;
  }

  public Optional<Expense> findById(Integer id) {
    return expenseRepository.findById(id);
  }

  public void deleteOne(Employee employee, Expense expense) {
    employee.getExpenses().remove(expense);
    employeeRepository.save(employee);
  }

  public void save(Expense expense) {
    this.expenseRepository.save(expense);
  }
}
