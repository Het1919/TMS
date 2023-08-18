package ai.ignosis.entities;

import java.util.*;

public class Tenant {
	
	private String tenantId;
	private String tenantName;
	private Set<AccountAggregator> accountAggregators;
	private Map<String, Set<Bank>> aggregatorBanks;
	

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public Set<AccountAggregator> getAccountAggregators() {
		return accountAggregators;
	}

	public void setAccountAggregators(Set<AccountAggregator> accountAggregators) {
		this.accountAggregators = accountAggregators;
	}

	public Map<String, Set<Bank>> getAggregatorBanks() {
		return aggregatorBanks;
	}

	public void setAggregatorBanks(Map<String, Set<Bank>> aggregatorBanks) {
		this.aggregatorBanks = aggregatorBanks;
	}

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", tenantName=" + tenantName + ", accountAggregators="
				+ accountAggregators + ", aggregatorBanks=" + aggregatorBanks + "]";
	}

	public Tenant(String tenantId, String tenantName, Set<AccountAggregator> accountAggregators,
			Map<String, Set<Bank>> aggregatorBanks) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.accountAggregators = accountAggregators;
		this.aggregatorBanks = aggregatorBanks;
	}

	public Tenant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}