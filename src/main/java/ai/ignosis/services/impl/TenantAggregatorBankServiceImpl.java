package ai.ignosis.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.ignosis.entities.TenantAggregatorBank;
import ai.ignosis.repositories.TenantAggregatorBankRepository;
import ai.ignosis.services.TenantAggregatorBankService;

@Service
public class TenantAggregatorBankServiceImpl implements TenantAggregatorBankService {
	
	@Autowired
	private TenantAggregatorBankRepository tenantAggregatorBankRepository;

	@Override
	public List<TenantAggregatorBank> getAllTenantsWithAggregatorsAndBanks() {
		return tenantAggregatorBankRepository.findAll();
	}

}
