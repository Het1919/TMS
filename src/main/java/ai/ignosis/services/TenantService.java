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
		
		for(AccountAggregator accAgg:accountAggregatorService.getAggregators())
		{
			if(String.valueOf(accAgg.getId()).equals(aggregatorsId) )
			{
				for(Bank b : bankService.getBanks())
				{
					if(String.valueOf(b.getBankId()).equals(bankId))
					{
						b.setGlobalStatus(Boolean.parseBoolean(selectedValue));
						System.out.println(b);
						return;
					}
				}
			}
		}
		
	}

//	 public Tenant saveTenant(Tenant tenant) {
//		    String prefix = "TID";
//		    String formattedCounter = String.format("%02d", counter);
//		    String randomID = prefix + formattedCounter;
//		    
//		    Set<Bank> banks = new HashSet<>(); // Initialize banks set here if needed
//		    
//		    tenant.setTenantId(randomID);
//		    tenant.setBanks(banks); // Set the initialized banks set
//		    
//		    counter++;
//		    tenants.put(randomID, tenant);
//		    return tenant;
//		}
//
//	
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
