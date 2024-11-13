package com.example.RewardsCalculator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RewardsCalculator.entity.Customer;
import com.example.RewardsCalculator.exception.CustomerNotFoundException;
import com.example.RewardsCalculator.model.Rewards;
import com.example.RewardsCalculator.repository.CustomerRepository;
import com.example.RewardsCalculator.service.RewardsService;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/customers")
public class RewardsController {

	final static Logger logger = LoggerFactory.getLogger(RewardsController.class);

    @Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") @NotNull @Min(1000) Long customerId){
    	
    	logger.info("----- finding customer by id");
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {	logger.error("===== customer not found");
        	throw new CustomerNotFoundException("Invalid / Missing customer Id");
        }
        
        logger.info("----- calculating rewards for customer");
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        
        logger.info("----- rewards calculated, response sent.");
        return new ResponseEntity<>(customerRewards,HttpStatus.OK);
    }


	

}
