package ai.ignosis.entities;

import java.util.*;

public class Tenant {
	
	private String tenantId;
	private String tenantName;
	
	private Set<Bank> banks;

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

	public Set<Bank> getBanks() {
		return banks;
	}

	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}

	public Tenant(String tenantId, String tenantName, Set<Bank> banks) {
		super();
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.banks = banks != null ? banks : new HashSet<>();
	}

	public Tenant() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Tenant [tenantId=" + tenantId + ", tenantName=" + tenantName + ", banks=" + banks + "]";
	}

}