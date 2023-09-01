package com.example.expensify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@JsonDeserialize(builder = Expense.Builder.class)
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String merchant;

  private String description;

  @JsonProperty("purchase_date")
  private LocalDate purchaseDate;

  private double amount;

  @OneToOne(cascade = CascadeType.ALL)
  private Status status;

  private Expense() {}

  private Expense(Builder builder) {
    this.merchant = builder.merchant;
    this.description = builder.description;
    this.purchaseDate = builder.purchaseDate;
    this.amount = builder.amount;
    this.status = Status.builder().state(Status.State.IN_REVIEW).build();
  }

  public Integer getId() {
    return this.id;
  }

  public String getMerchant() {
    return this.merchant;
  }

  public String getDescription() {
    return this.description;
  }

  public LocalDate getPurchaseDate() {
    return this.purchaseDate;
  }

  public double getAmount() {
    return this.amount;
  }

  public Status getStatus() {
    return this.status;
  }

  public static Builder builder() {
    return new Builder();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {

    private String merchant;

    private String description;

    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    private double amount;

    public Builder merchant(String merchant) {
      this.merchant = merchant;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder purchaseDate(LocalDate purchaseDate) {
      this.purchaseDate = purchaseDate;
      return this;
    }

    public Builder amount(double amount) {
      this.amount = amount;
      return this;
    }

    public Expense build() {
      return new Expense(this);
    }
  }
}
