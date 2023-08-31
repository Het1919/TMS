package ai.ignosis.services;

import java.util.*;

import ai.ignosis.entities.Tenant;

public interface TenantService {

	public void saveTenant(String tenantName);

	public void addAggregatorInTenant(int tenantId, String name, List<String> selectedBanks);

	public List<Tenant> getAllTenants();

	public Tenant getTenantById(int tenantId);

}
