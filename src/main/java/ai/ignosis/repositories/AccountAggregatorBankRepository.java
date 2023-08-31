package ai.ignosis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.AccountAggregatorBanks;
import ai.ignosis.entities.Bank;

@Repository
public interface AccountAggregatorBankRepository extends JpaRepository<AccountAggregatorBanks, Integer>{

	public AccountAggregatorBanks findByAccountAggregatorAndBank(AccountAggregator accountAggregator, Bank bank);
	
}
