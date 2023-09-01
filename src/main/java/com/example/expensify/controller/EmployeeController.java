package com.example.expensify.controller;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.entity.Status;
import com.example.expensify.service.EmployeeService;
import com.example.expensify.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EmployeeController {

  @Autowired private EmployeeService employeeService;

  @Autowired private ExpenseService expenseService;

  @GetMapping("/employees")
  public Iterable<Employee> findAllEmployees() {
    return employeeService.findAll();
  }

  @GetMapping("/employees/{employee_id}/expenses")
  public Iterable<Expense> findExpensesByEmployeeId(
      @PathVariable(value = "employee_id") Integer employeeId) {
    return employeeService
        .findById(employeeId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        .getExpenses();
  }

  @PostMapping("/employees/{employee_id}/expenses")
  public Expense addOneExpense(
      @PathVariable(value = "employee_id") Integer employeeId, @RequestBody Expense expense) {
    Employee employee =
        employeeService
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return expenseService.addOne(employee, expense);
  }

  @DeleteMapping("/employees/{employee_id}/expenses/{expense_id}")
  public ResponseEntity<Void> deleteOneExpense(
      @PathVariable(value = "employee_id") Integer employeeId,
      @PathVariable(value = "expense_id") Integer expenseId) {
    Employee employee =
        employeeService
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    Expense expense =
        expenseService
            .findById(expenseId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    expenseService.deleteOne(employee, expense);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/expenses/{expense_id}/status")
  public ResponseEntity<Void> updateExpenseStatus(
      @PathVariable(value = "expense_id") Integer expenseId, @RequestBody Status updatedStatus) {
    Expense expense =
        expenseService
            .findById(expenseId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    expense.getStatus().changeTo(updatedStatus);
    expenseService.save(expense);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
