package ai.ignosis.services;

import java.util.List;

import ai.ignosis.entities.AccountAggregator;

public interface AccountAggregatorService {

	public void addAggregator(String aggregatorName, List<String> bankOptions) ;

	public List<AccountAggregator> getAllAccountAggregators();

	public AccountAggregator getAggregatorById(int id);
}
