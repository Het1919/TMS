package ai.ignosis.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.AccountAggregatorBanks;
import ai.ignosis.entities.Bank;
import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.TenantAggregatorBank;
import ai.ignosis.exceptions.ResourseNotFoundException;
import ai.ignosis.repositories.AccountAggregatorBankRepository;
import ai.ignosis.repositories.AccountAggregatorRepository;
import ai.ignosis.repositories.BankRepository;
import ai.ignosis.repositories.TenantAggregatorBankRepository;
import ai.ignosis.repositories.TenantRepository;
import ai.ignosis.services.TenantService;

@Service
public class TenantServiceImpl implements TenantService{

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
		
		List<AccountAggregatorBanks> list = accountAggregatorBankRepository.findAll();
		
		Set<Bank> banks = new HashSet<>();
		for(String bankName : selectedBanks)
		{
			List<Bank> b1 = bankRepository.findByBankName(bankName);
			banks.add(b1.get(0));

			TenantAggregatorBank tenantAggregatorBank = new TenantAggregatorBank();
			tenantAggregatorBank.setTenant(tenant);
			tenantAggregatorBank.setAccountAggregator(aggregatorByName.get(0));
			tenantAggregatorBank.setBank(b1.get(0));
			
			for(AccountAggregatorBanks ag: list)
			{
				if(ag.getAccountAggregator().getName().equals(aggregatorByName.get(0).getName()) && ag.getBank().getBankId() == b1.get(0).getBankId())
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

	public List<Tenant> getAllTenants() {
		return tenantRepository.findAll();
	}

	@Override
	public Tenant getTenantById(int tenantId) {
		Tenant tenant = tenantRepository.findById(tenantId)
				.orElseThrow(() -> new ResourseNotFoundException("tenant not exist with id :: "+tenantId));
		return tenant;
	}
}