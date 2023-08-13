package ai.ignosis.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ai.ignosis.entities.Tenant;
import ai.ignosis.services.TenantService;

@Controller
public class TenantController {
	
	@Autowired
	private TenantService tenantService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		 List<String> bankOptions = Arrays.asList("SBI", "BOB", "ICICI", "HDFC", "PNB");
	     List<Tenant> tenants = tenantService.getAllTenants();
	     
	     Map<String, Boolean> bankStatusMap = TenantService.getBankStatusMap();
	     
	     model.addAttribute("bankStatusMap", bankStatusMap);
	     model.addAttribute("tenants", tenants);
	     model.addAttribute("bankOptions", bankOptions);
	     return "index";
	}
	
	@PostMapping("/addTenant")
	public String addTenant(@RequestParam String tenantName) {
	    Tenant tenant = new Tenant();
	    tenant.setTenantName(tenantName);
	    
	    tenantService.saveTenant(tenant);
	    return "redirect:/";
	}

	@PostMapping("/addBank/{tenantId}")
	public String addBankToTenant(@PathVariable String tenantId,
	                              @RequestParam List<String> bankOptions) {
		    
			Map<String,Boolean> bankStatusMap = TenantService.getBankStatusMap();
			
	        for (String bankName : bankOptions) {
	        	boolean bankStatus = bankStatusMap.get(bankName);
	            tenantService.addBankToTenant(tenantId, bankName, bankStatus);
	        }
	        return "redirect:/";
	}
	
	@PostMapping("/toggleBankStatus")
	public ResponseEntity<String> toggleBankStatus(@RequestParam String bankName, @RequestParam boolean newStatus) {
	    // Update the bank status in your service
	    tenantService.toggleBankStatus(bankName, newStatus);
	    return ResponseEntity.ok().build();
	}
	
	@PostMapping("/updateBankStatus")
	public ResponseEntity<String> updateBankStatus(@RequestParam String bankName, @RequestParam boolean newStatus,@RequestParam String tenantId) {
	    tenantService.updateBankStatus(bankName, newStatus,tenantId);
	    return ResponseEntity.ok().build();
	}
	
	@PostMapping("/updateGlobalBankStatus")
	public ResponseEntity<String> updateGlobalBankStatus(@RequestParam String bankName, @RequestParam boolean newStatus) {
	    tenantService.updateGlobalBankStatus(bankName, newStatus);
	    return ResponseEntity.ok().build();
	}

}