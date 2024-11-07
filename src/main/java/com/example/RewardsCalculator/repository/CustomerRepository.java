package com.example.RewardsCalculator.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.RewardsCalculator.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	 public Customer findByCustomerId(Long customerId);
}
