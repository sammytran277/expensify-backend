package com.example.expensify.repository;

import com.example.expensify.entity.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {}
