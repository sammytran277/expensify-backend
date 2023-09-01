package com.example.expensify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {

  private Reviewer() {}

  public Reviewer(String username, String password) {
    super(username, password, Role.ROLE_REVIEWER);
  }
}
