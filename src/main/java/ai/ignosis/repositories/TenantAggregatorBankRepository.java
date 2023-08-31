package ai.ignosis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.Bank;
import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.TenantAggregatorBank;

@Repository
public interface TenantAggregatorBankRepository extends JpaRepository<TenantAggregatorBank, Integer>{

	public TenantAggregatorBank findByTenantAndAccountAggregatorAndBank(Tenant tenant, AccountAggregator accountAggregator,
			Bank bank);
	
}
