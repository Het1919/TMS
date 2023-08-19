package ai.ignosis.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.Bank;

@Service
public class TenantService {

	private final ConcurrentHashMap<String, Tenant> tenants = new ConcurrentHashMap<>();

	@Autowired
	private AccountAggregatorService accountAggregatorService;

	@Autowired
	private BankService bankService;

	private static int counter = 1;

	public List<Tenant> getAllTenants() {
		return new ArrayList<>(tenants.values());
	}

	public void handleDropdownChange(String selectedValue, String aggregatorsId, String bankId) {

//		for (AccountAggregator accAgg : accountAggregatorService.getAggregators()) {
//			if (String.valueOf(accAgg.getId()).equals(aggregatorsId)) {
//				for (Bank b : accAgg.getBanks()) {
//					if (String.valueOf(b.getBankId()).equals(bankId)) {
//						b.setGlobalStatus(Boolean.parseBoolean(selectedValue));
//						return;
//					}
//				}
//			}
//		}
		
		List<Tenant> allTenants = getAllTenants();
        
		for(Tenant t: allTenants)
		{
			Set<Bank> banks = t.getAggregatorBanks().get(aggregatorsId);
			
			for(Bank b: banks)
			{
				if(String.valueOf(b.getBankId()).equals(bankId))
				{
					b.setGlobalStatus(Boolean.parseBoolean(selectedValue));
					b.setStatus(Boolean.parseBoolean(selectedValue));
				}
			}
			
		}	
		
	}

	public void saveTenant(String tenantName) {
		String prefix = "TID";
		String formattedCounter = String.format("%02d", counter);
		String randomID = prefix + formattedCounter;

		Tenant t = new Tenant();
		t.setTenantId(randomID);
		t.setTenantName(tenantName);
		t.setAccountAggregators(new HashSet<AccountAggregator>());
		t.setAggregatorBanks(new HashMap<String, Set<Bank>>());

		counter++;
		tenants.put(randomID, t);
	}

	public Tenant getTenant(String tenantId) {
		System.out.println(tenantId);
		System.out.println(tenants);
		return tenants.get(tenantId);
	}

	public void handleDropdownChangeForTenant(String selectedValue1, String tenantId, String aggregatorsId,
			String bankId) {

		List<Tenant> allTenants = getAllTenants();
		for (Tenant t : allTenants) {
			if (t.getTenantId().equals(tenantId)) {
				Set<Bank> banks = t.getAggregatorBanks().get(aggregatorsId);
				for (Bank b : banks) {
					if (String.valueOf(b.getBankId()).equals(bankId)) {
						b.setStatus(Boolean.parseBoolean(selectedValue1));
						return;
					}
				}
			}
		}
	}

//	public Tenant getTenantById(String tenantId) {
//	    return tenants.get(tenantId);
//	}
//
//	public void addBankToTenant(String tenantId, String bankName, boolean bankStatus) {
//	    Tenant tenant = tenants.get(tenantId);
//	    if (tenant != null) {
//	        boolean found = false;
//	        for (Bank bank : tenant.getBanks()) {
//	            if (bank.getBankName().equals(bankName)) {
//	                bank.setStatus(bankStatus);
//	                bank.setGlobalStatus(bankStatusMap.get(bank.getBankName()));
//	                found = true;
//	                break;
//	            }
//	        }
//	        if (!found) {
//	            Bank bank = new Bank(bankName, bankStatus);
//	            tenant.getBanks().add(bank);
//	        }
//	    }
//	}
//	
//	public void toggleBankStatus(String bankName, boolean newStatus) {
//	    for (Tenant tenant : tenants.values()) {
//	        for (Bank bank : tenant.getBanks()) {
//	            if (bank.getBankName().equals(bankName)) {
//	                bank.setStatus(newStatus);
//	                return;
//	            }
//	        }
//	    }
//	}
//	
//	public void updateBankStatus(String bankName, boolean newStatus,String tenantId) {
//		//System.out.println(tenantId);
//	    Tenant tenant = tenants.get(tenantId);
//	    for (Bank bank : tenant.getBanks()) {
//            if (bank.getBankName().equals(bankName)) {
//                bank.setStatus(newStatus);
//                return;
//            }
//        }
//	}
//	
//	public void updateGlobalBankStatus(String bankName, boolean newStatus) {
//	    bankStatusMap.put(bankName, newStatus);
//	}

}
