package ai.ignosis.entities;

import java.util.*;

public class Tenant {
	
	private String tenantId;
	private String tenantName;
	private List<AccountAggregator> accountAggregators;
	private Map<String, List<AccountAggregator>> aggregatorBanks;
	

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

	public List<AccountAggregator> getAccountAggregators() {
		return accountAggregators;
	}

	public void setAccountAggregators(List<AccountAggregator> accountAggregators) {
		this.accountAggregators = accountAggregators;
	}

	public Map<String, List<AccountAggregator>> getAggregatorBanks() {
		return aggregatorBanks;
	}

	public void setAggregatorBanks(Map<String, List<AccountAggregator>> aggregatorBanks) {
		this.aggregatorBanks = aggregatorBanks;
	}

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", tenantName=" + tenantName + ", accountAggregators="
				+ accountAggregators + ", aggregatorBanks=" + aggregatorBanks + "]";
	}

	public Tenant(String tenantId, String tenantName, List<AccountAggregator> accountAggregators,
			Map<String, List<AccountAggregator>> aggregatorBanks) {
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