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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ExpensifyApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExpensifyApplication.class, args);
  }

  @Bean
  public CommandLineRunner initDatabase(
      EmployeeRepository employeeRepository,
      ReviewerRepository reviewerRepository,
      PasswordEncoder passwordEncoder) {
    return args -> {
      List<Employee> employees =
          List.of(
              new Employee(
                  "employee_1",
                  passwordEncoder.encode("password"),
                  List.of(
                      Expense.builder()
                          .merchant("merchant_1")
                          .description("description_1")
                          .purchaseDate(LocalDate.now())
                          .amount(123.45)
                          .build())),
              new Employee("employee_2", passwordEncoder.encode("password")),
              new Employee("employee_3", passwordEncoder.encode("password")));
      Reviewer reviewer = new Reviewer("reviewer_1", passwordEncoder.encode("password"));
      Reviewer anotherReviewer = new Reviewer("reviewer_2", passwordEncoder.encode("password"));

      employees.forEach(employeeRepository::save);
      reviewerRepository.save(reviewer);
      reviewerRepository.save(anotherReviewer);
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
