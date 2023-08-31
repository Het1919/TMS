package ai.ignosis.services;

import java.util.List;

import ai.ignosis.entities.TenantAggregatorBank;

public interface TenantAggregatorBankService {

	public List<TenantAggregatorBank> getAllTenantsWithAggregatorsAndBanks();
}
