package ai.ignosis.services;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.TenantAggregatorBank;
import ai.ignosis.repositories.AccountAggregatorBankRepository;
import ai.ignosis.repositories.AccountAggregatorRepository;
import ai.ignosis.repositories.BankRepository;
import ai.ignosis.repositories.TenantAggregatorBankRepository;
import ai.ignosis.repositories.TenantRepository;
import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.AccountAggregatorBanks;
import ai.ignosis.entities.Bank;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private AccountAggregatorRepository accountAggregatorRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private TenantAggregatorBankRepository tenantAggregatorBankRepository;
	
	@Autowired
	private AccountAggregatorBankRepository accountAggregatorBankRepository;

	public void saveTenant(String tenantName) {
		Tenant t = new Tenant();
		t.setTenantName(tenantName);
		tenantRepository.save(t);
	}

	public void addAggregatorInTenant(int tenantId, String name, List<String> selectedBanks) {
		
		Tenant tenant = tenantRepository.findById(tenantId).get();
		List<AccountAggregator> aggregatorByName = accountAggregatorRepository.findByName(name);
		
		Set<AccountAggregator> s = new HashSet<>(aggregatorByName);
		
		List<AccountAggregatorBanks> list = accountAggregatorBankRepository.findAll();
		
		Set<Bank> banks = new HashSet<>();
		for(String bankName : selectedBanks)
		{
			List<Bank> b1 = bankRepository.findByBankName(bankName);
			banks.add(b1.get(0));

			TenantAggregatorBank tenantAggregatorBank = new TenantAggregatorBank();
			tenantAggregatorBank.setTenant(tenant);
			tenantAggregatorBank.setAggregator(aggregatorByName.get(0));
			tenantAggregatorBank.setBank(b1.get(0));
			
			for(AccountAggregatorBanks ag: list)
			{
				if(ag.getAggregator().getName().equals(aggregatorByName.get(0).getName()) && ag.getBank().getBankId() == b1.get(0).getBankId())
				{
					tenantAggregatorBank.setStatus(ag.isGlobalStatus());
					tenantAggregatorBankRepository.save(tenantAggregatorBank);
				}
			}
		}
		
		Set<AccountAggregator> accountAggregators = tenant.getAccountAggregators();
		accountAggregators.addAll(aggregatorByName);
		accountAggregatorRepository.saveAll(aggregatorByName);
		tenantRepository.save(tenant);
	}

}
