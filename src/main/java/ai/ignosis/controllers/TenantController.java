package ai.ignosis.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.Bank;
import ai.ignosis.entities.Tenant;
import ai.ignosis.services.AccountAggregatorService;
import ai.ignosis.services.BankService;
import ai.ignosis.services.TenantService;

@Controller
public class TenantController {

	@Autowired
	private TenantService tenantService;

	@Autowired
	private BankService bankService;

	@Autowired
	private AccountAggregatorService accountAggregatorService;

	@GetMapping("/")
	public String home(Model model) {
//		 List<String> bankOptions = Arrays.asList("SBI", "BOB", "ICICI", "HDFC", "PNB");
		List<Tenant> tenants = tenantService.getAllTenants();
//	     
//	     Map<String, Boolean> bankStatusMap = TenantService.getBankStatusMap();
//	     
//	     model.addAttribute("bankStatusMap", bankStatusMap);
		model.addAttribute("tenants", tenants);
//	     model.addAttribute("bankOptions", bankOptions);

		List<AccountAggregator> accountAggregators = accountAggregatorService.getAggregators();
		model.addAttribute("accountAggregators", accountAggregators);

		model.addAttribute("bankOptions", bankService.getBanks());
		return "index";
	}

	@PostMapping("/handleDropdownChange")
	public String handleDropdownChange(@RequestParam("selectedValue") String selectedValue,
			@RequestParam("aggregators_id") String aggregatorsId, @RequestParam("bank_id") String bankId) {
		tenantService.handleDropdownChange(selectedValue, aggregatorsId, bankId);
		return "redirect:/";
	}

	@PostMapping("/addAggregator")
	public String addAggregator(@RequestParam String aggregatorName, @RequestParam List<String> bankOptions) {
		accountAggregatorService.addAggregator(aggregatorName, bankOptions);
		return "redirect:/";
	}

	@PostMapping("/addTenant")
	public String addTenant(@RequestParam String tenantName) {
		tenantService.saveTenant(tenantName);
		return "redirect:/";
	}

//	@PostMapping("/addAggregatorInTenant")
//    public String addAggregatorInTenant(@RequestParam String aggregatorId,Model model)
//    {
//		AccountAggregator newAgg = accountAggregatorService.getAccountAggregatorById(aggregatorId);
//		model.addAttribute("aggregator", newAgg);
//    	return "redirect:/";
//    }

	@GetMapping("/getBanks")
	@ResponseBody
	public List<Bank> getBanks(@RequestParam String selected, Model model) {
		List<AccountAggregator> aggregators = accountAggregatorService.getAggregators();
		List<Bank> banks = new ArrayList<>();

		for (AccountAggregator agg : aggregators) {
			if (String.valueOf(agg.getId()).equals(selected)) {
				banks.addAll(agg.getBanks());
				break;
			}
		}

		return banks;
	}

	@PostMapping("/addAggregatorInTenant/{tenantId}")
	public String addBankToTenant(@PathVariable String tenantId, @RequestParam String aggregatorId,
			@RequestParam List<String> bankOptions) {

		Tenant tenant = tenantService.getTenant(tenantId);
		AccountAggregator agg = accountAggregatorService.getAccountAggregatorById(aggregatorId);

		Map<String, Set<Bank>> aggregatorBanks = tenant.getAggregatorBanks();

		Set<Bank> banks = new HashSet<>();
		for (Bank b : agg.getBanks()) {
		    if (bankOptions.contains(String.valueOf(b.getBankId()))) {
		        boolean shouldAdd = true; // Flag to indicate whether the bank should be added

		        if (aggregatorBanks.containsKey(aggregatorId)) {
		            for (Bank b1 : aggregatorBanks.get(aggregatorId)) {
		                if (b1.getBankId() == b.getBankId()) {
		                    shouldAdd = false; // Bank already exists in aggregatorBanks
		                    break;
		                }
		            }
		        }

		        // Check if the bank already exists in the banks list
		        if (shouldAdd) {
		            boolean alreadyAdded = false;
		            for (Bank addedBank : banks) {
		                if (addedBank.getBankId() == b.getBankId()) {
		                    alreadyAdded = true;
		                    break;
		                }
		            }	

		            if (!alreadyAdded) {
		                Bank newBank = new Bank(b);
		                banks.add(newBank);
		            }
		        }
		    }
		}


		tenant.getAccountAggregators().add(agg);

		if (aggregatorBanks.containsKey(aggregatorId)) {
			aggregatorBanks.get(aggregatorId).addAll(banks);
		} else {
			aggregatorBanks.put(aggregatorId, banks);
		}

		System.out.println(tenant);

		return "redirect:/";
	}
//	
//	@PostMapping("/toggleBankStatus")
//	public ResponseEntity<String> toggleBankStatus(@RequestParam String bankName, @RequestParam boolean newStatus) {
//	    // Update the bank status in your service
//	    tenantService.toggleBankStatus(bankName, newStatus);
//	    return ResponseEntity.ok().build();
//	}
//	
//	@PostMapping("/updateBankStatus")
//	public ResponseEntity<String> updateBankStatus(@RequestParam String bankName, @RequestParam boolean newStatus,@RequestParam String tenantId) {
//	    tenantService.updateBankStatus(bankName, newStatus,tenantId);
//	    return ResponseEntity.ok().build();
//	}
//	
//	@PostMapping("/updateGlobalBankStatus")
//	public ResponseEntity<String> updateGlobalBankStatus(@RequestParam String bankName, @RequestParam boolean newStatus) {
//	    tenantService.updateGlobalBankStatus(bankName, newStatus);
//	    return ResponseEntity.ok().build();
//	}

}