package ai.ignosis.services;

import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

import org.springframework.stereotype.Service;

import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.Bank;

@Service
public class TenantService {
	
	private final ConcurrentHashMap<String, Tenant> tenants = new ConcurrentHashMap<>();
	
	private static Map<String, Boolean> bankStatusMap;

	public static Map<String, Boolean> getBankStatusMap() {
		return bankStatusMap;
	}

	static {
		bankStatusMap = new HashMap<>();
        bankStatusMap.put("SBI", true);
        bankStatusMap.put("BOB",true);
        bankStatusMap.put("ICICI", false);
        bankStatusMap.put("HDFC", false);
        bankStatusMap.put("PNB", true);
	}
	
	private static int counter = 1;
	
	 public List<Tenant> getAllTenants() {
	        return new ArrayList<>(tenants.values());
	 }

	 public Tenant saveTenant(Tenant tenant) {
		    String prefix = "TID";
		    String formattedCounter = String.format("%02d", counter);
		    String randomID = prefix + formattedCounter;
		    
		    Set<Bank> banks = new HashSet<>(); // Initialize banks set here if needed
		    
		    tenant.setTenantId(randomID);
		    tenant.setBanks(banks); // Set the initialized banks set
		    
		    counter++;
		    tenants.put(randomID, tenant);
		    return tenant;
		}

	
	public Tenant getTenantById(String tenantId) {
	    return tenants.get(tenantId);
	}

	public void addBankToTenant(String tenantId, String bankName, boolean bankStatus) {
	    Tenant tenant = tenants.get(tenantId);
	    if (tenant != null) {
	        boolean found = false;
	        for (Bank bank : tenant.getBanks()) {
	            if (bank.getBankName().equals(bankName)) {
	                bank.setStatus(bankStatus);
	                bank.setGlobalStatus(bankStatusMap.get(bank.getBankName()));
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            Bank bank = new Bank(bankName, bankStatus);
	            tenant.getBanks().add(bank);
	        }
	    }
	}
	
	public void toggleBankStatus(String bankName, boolean newStatus) {
	    for (Tenant tenant : tenants.values()) {
	        for (Bank bank : tenant.getBanks()) {
	            if (bank.getBankName().equals(bankName)) {
	                bank.setStatus(newStatus);
	                return;
	            }
	        }
	    }
	}
	
	public void updateBankStatus(String bankName, boolean newStatus,String tenantId) {
		//System.out.println(tenantId);
	    Tenant tenant = tenants.get(tenantId);
	    for (Bank bank : tenant.getBanks()) {
            if (bank.getBankName().equals(bankName)) {
                bank.setStatus(newStatus);
                return;
            }
        }
	}
	
	public void updateGlobalBankStatus(String bankName, boolean newStatus) {
	    bankStatusMap.put(bankName, newStatus);
	}

}
