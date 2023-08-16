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

	@Autowired
	public AccountAggregatorService(BankService bankService) {
		this.bankService = bankService;
		initializeAggregators();
	}

	private void initializeAggregators() {
		List<Bank> list = bankService.getBanks();
		list = bankService.getBanks();
		
		AccountAggregator a1 = new AccountAggregator();
		a1.setId(1);
		a1.setName("Aggregator1");
		Set<Bank> banks = new HashSet<>();
		banks.add(list.get(0));
		banks.add(list.get(1));
		banks.add(list.get(2));
		banks.add(list.get(3));
		a1.setBanks(banks);
		
		AccountAggregator a2 = new AccountAggregator();
		a2.setId(2);
		a2.setName("Aggregator2");
		Set<Bank> banks2 = new HashSet<>();
		banks2.add(list.get(1));
		banks2.add(list.get(3));
		banks2.add(list.get(4));
		a2.setBanks(banks2);

		AccountAggregator a3 = new AccountAggregator();
		a3.setId(3);
		a3.setName("Aggregator3");
		Set<Bank> banks3 = new HashSet<>();
		banks3.add(list.get(2));
		banks3.add(list.get(4));
		a3.setBanks(banks3);

		aggregators = new ArrayList<>();
		aggregators.add(a1);
		aggregators.add(a2);
		aggregators.add(a3);
	}

	public List<AccountAggregator> getAggregators() {
		return aggregators;
	}

}
