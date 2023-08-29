package com.example.expensify;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.entity.Reviewer;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ReviewerRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpensifyApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExpensifyApplication.class, args);
  }

  @Bean
  public CommandLineRunner initDatabase(
      EmployeeRepository employeeRepository, ReviewerRepository reviewerRepository) {
    return args -> {
      List<Employee> employees =
          List.of(
              new Employee(
                  "employee_1",
                  "password",
                  List.of(
                      Expense.builder()
                          .merchant("merchant_1")
                          .description("description_1")
                          .purchaseDate(LocalDate.now())
                          .amount(123.45)
                          .build())),
              new Employee("employee_2", "password"),
              new Employee("employee_3", "password"));
      Reviewer reviewer = new Reviewer("reviewer_1", "password");
      Reviewer anotherReviewer = new Reviewer("reviewer_2", "password");

      employees.forEach(employeeRepository::save);
      reviewerRepository.save(reviewer);
      reviewerRepository.save(anotherReviewer);
    };
  }
}
