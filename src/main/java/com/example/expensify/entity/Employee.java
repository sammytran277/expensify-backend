package com.example.expensify.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends ExpensifyUser {

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Expense> expenses;

  private Employee() {}

  public Employee(String username, String password) {
    this(username, password, new ArrayList<>());
  }

  public Employee(String username, String password, List<Expense> expenses) {
    super(username, password, Role.EMPLOYEE);
    this.expenses = expenses;
  }

  public List<Expense> getExpenses() {
    return this.expenses;
  }
}
