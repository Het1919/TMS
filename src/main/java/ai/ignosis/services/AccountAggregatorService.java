package ai.ignosis.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.Bank;

@Service
public class AccountAggregatorService {

	private BankService bankService;

	List<AccountAggregator> aggregators;
	List<Bank> list;
	
	static int aggId = 1;

	@Autowired
	public AccountAggregatorService(BankService bankService) {
		this.bankService = bankService;
	}

	public List<AccountAggregator> getAggregators() {
		return aggregators;
	}

	public void addAggregator(String aggregatorName, List<String> bankOptions) {
		
		AccountAggregator a = new AccountAggregator();
		a.setId(aggId++);
		a.setName(aggregatorName);
		Set<Bank> banks = new HashSet<>();
		for(String bankName:bankOptions)
		{
			Bank b = bankService.getBankByName(bankName);
		
			if(b != null)
			{
				Bank b1 = new Bank(b);
				banks.add(b1);
			}
		}
		a.setBanks(banks);
		if(aggregators == null) {
			aggregators = new ArrayList<>();
		}
		aggregators.add(a);
	}
	
	public AccountAggregator getAccountAggregatorById(String aaId)
	{
		for(AccountAggregator a: aggregators)
		{
			if(String.valueOf(a.getId()).equals(aaId))
			{
				return a;
			}
		}
		return null;
	}

	public Set<Bank> addAggregatorInTenant(String aggregatorId) {
		return getAccountAggregatorById(aggregatorId).getBanks();
	}
	

	 public List<Bank> getAggBanks(String id)
	 {
		 List<Bank> banks = new ArrayList<>();
		 for(AccountAggregator agg : aggregators)
		 {
			 if(String.valueOf(agg.getId()).equals(id))
			 {
				 for(Bank b : agg.getBanks()) {
					 banks.add(b);
				 }
			 }
		 }
		 return banks;
	 }

}
