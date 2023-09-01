package com.example.expensify.repository;

import com.example.expensify.entity.Reviewer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReviewerRepository extends CrudRepository<Reviewer, Integer> {

  Optional<Reviewer> findByUsername(String username);
}
