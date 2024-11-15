package com.example.RewardsCalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.RewardsCalculator.controller.RewardsController;
import com.example.RewardsCalculator.entity.Customer;
import com.example.RewardsCalculator.entity.Transaction;
import com.example.RewardsCalculator.exception.CustomerNotFoundException;
import com.example.RewardsCalculator.exception.GlobalExceptionHandler;
import com.example.RewardsCalculator.model.Rewards;
import com.example.RewardsCalculator.repository.CustomerRepository;
import com.example.RewardsCalculator.repository.TransactionRepository;
import com.example.RewardsCalculator.service.RewardsService;


//@SpringBootTest
//@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RewardsController.class)
class RewardsCalculatorApplicationTests {
	
	@MockBean
	RewardsService rewardsService;
	
	@MockBean
	CustomerRepository customerRepository;
	
	@MockBean
	TransactionRepository transactionRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	GlobalExceptionHandler globalExceptionHandler;

	
	@Test
	void contextLoads() {
		
		
	}
	
	@Test
	public void getRewardsbyCustomerIdTest_InvalidCustomer() throws Exception{
		
		// Mock service to return invalid rewards
		when(rewardsService.getRewardsByCustomerId(999L)).thenThrow(new CustomerNotFoundException("customer invalid"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/customers/999/rewards"))
							.andExpect(MockMvcResultMatchers.status().isInternalServerError());
		
	}
	
	@Test
	public void getRewardsByCustomerIdTest_CustomerNotFound() throws Exception{	
		
		// Mock service to return customer that doesn't exists
		when(rewardsService.getRewardsByCustomerId(1004L)).thenThrow(new CustomerNotFoundException("customer Not found"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/customers/1004/rewards")).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	  public void getRewardsByCustomerIdTest_ValidCustomer() throws Exception {
		
		 Customer mockCustomer = new Customer();
		 mockCustomer.setCustomerId(1001L);
		 mockCustomer.setCustomerName("John Doe");
	    
        Rewards rewards = new Rewards();
        rewards.setCustomerId(1001L);
        rewards.setLastMonthRewardPoints(125);
        rewards.setLastSecondMonthRewardPoints(210);
        rewards.setLastThirdMonthRewardPoints(270);
        rewards.setTotalRewards(605);

        when(customerRepository.findByCustomerId(1001L)).thenReturn(mockCustomer);
        
        
        // Mock service to return valid rewards
        when(rewardsService.getRewardsByCustomerId(1001L)).thenReturn(rewards);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1001/rewards"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(1001L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalRewards").value(605));
    }

}
