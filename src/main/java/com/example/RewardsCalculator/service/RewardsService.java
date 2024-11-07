package com.example.RewardsCalculator.service;

import com.example.RewardsCalculator.model.Rewards;

public interface RewardsService {
	public Rewards getRewardsByCustomerId(Long customerId);
}
