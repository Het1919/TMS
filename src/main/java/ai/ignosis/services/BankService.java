package ai.ignosis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.ignosis.entities.AccountAggregatorBanks;
import ai.ignosis.entities.Bank;
import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.TenantAggregatorBank;
import ai.ignosis.repositories.AccountAggregatorBankRepository;
import ai.ignosis.repositories.BankRepository;
import ai.ignosis.repositories.TenantAggregatorBankRepository;
import ai.ignosis.repositories.TenantRepository;

import java.util.*;

@Service
public class BankService {

	private final BankRepository bankRepository;

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private TenantAggregatorBankRepository tenantAggregatorBankRepository;

	@Autowired
	private AccountAggregatorBankRepository accountAggregatorBankRepository;

	@Autowired
	public BankService(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}

	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

	public List<Bank> getBankByName(String name) {
		List<Bank> bank = bankRepository.findByBankName(name);
		return bank;
	}

	public void updateGlobalStatus(int aggId, int bankId, boolean status) {

		List<AccountAggregatorBanks> list = accountAggregatorBankRepository.findAll();
		for (AccountAggregatorBanks ab : list) {
			if (ab.getAggregator().getId() == aggId && ab.getBank().getBankId() == bankId) {
				ab.setGlobalStatus(status);
				accountAggregatorBankRepository.save(ab);
				break;
			}
		}

		List<Tenant> listOfTenants = tenantRepository.findAll();

		for (Tenant t : listOfTenants) {
			try {
				List<TenantAggregatorBank> all = tenantAggregatorBankRepository.findAll();
				for (TenantAggregatorBank tab : all) {
					if (tab.getTenant().getTenantId() == (t.getTenantId()) && tab.getAggregator().getId() == aggId
							&& tab.getBank().getBankId() == bankId) {
						tab.setStatus(status);
						tenantAggregatorBankRepository.save(tab);
					}
				}
			} catch (Exception e) {

			}
		}

	}

	public void createBanks(String name) {
		Bank b = new Bank();
		b.setBankName(name);
		bankRepository.save(b);
	}

	public void updateLocalStatus(int tId, int agId, int bId, boolean status) {

		List<TenantAggregatorBank> all = tenantAggregatorBankRepository.findAll();
		for (TenantAggregatorBank tab : all) {
			if (tab.getTenant().getTenantId() == tId && tab.getAggregator().getId() == agId
					&& tab.getBank().getBankId() == bId) {
				tab.setStatus(status);
				tenantAggregatorBankRepository.save(tab);
			}
		}

	}
}
