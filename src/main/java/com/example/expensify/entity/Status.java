package com.example.expensify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "statuses")
public class Status {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Enumerated(value = EnumType.STRING)
  private State state;

  @JsonProperty("reviewed_by")
  private String reviewedBy;

  @JsonProperty("review_date")
  private LocalDate reviewDate;

  private String comment;

  private Status() {}

  private Status(Builder builder) {
    this.state = builder.state;
    this.reviewedBy = builder.reviewedBy;
    this.reviewDate = builder.reviewDate;
    this.comment = builder.comment;
  }

  public void changeTo(Status status) {
    this.state = status.getState();
    this.reviewedBy = status.getReviewedBy();
    this.reviewDate = status.getReviewDate();
    this.comment = status.getComment();
  }

  public State getState() {
    return this.state;
  }

  public String getReviewedBy() {
    return this.reviewedBy;
  }

  public LocalDate getReviewDate() {
    return this.reviewDate;
  }

  public String getComment() {
    return this.comment;
  }

  public static Builder builder() {
    return new Builder();
  }

  public enum State {
    IN_REVIEW,
    APPROVED,
    REJECTED
  }

  public static class Builder {

    private State state;

    private String reviewedBy;

    private LocalDate reviewDate;

    private String comment;

    public Builder state(State state) {
      this.state = state;
      return this;
    }

    public Builder reviewedBy(String reviewedBy) {
      this.reviewedBy = reviewedBy;
      return this;
    }

    public Builder reviewDate(LocalDate reviewedDate) {
      this.reviewDate = reviewedDate;
      return this;
    }

    public Builder comment(String comment) {
      this.comment = comment;
      return this;
    }

    public Status build() {
      return new Status(this);
    }
  }
}
