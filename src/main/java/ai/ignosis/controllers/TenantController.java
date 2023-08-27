package ai.ignosis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.dto.AccountAggregatorSelectedBankDto;
import ai.ignosis.dto.TenantDto;
import ai.ignosis.services.TenantService;

@RestController
@RequestMapping("/api")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	
	@PostMapping("/tenants")
	public ResponseEntity<String> addTenant(@RequestBody TenantDto tenantDto) {
		tenantService.saveTenant(tenantDto.getName());
		return new ResponseEntity<>("Tenant Created successfully",HttpStatus.CREATED);
	}
	
	@PostMapping("/aggregators/{id}")
	public ResponseEntity<String> addAggregatorInTenant(@PathVariable(name = "id") int tenantId,@RequestBody AccountAggregatorSelectedBankDto accountAggregatorSelectedBankDto) {

		tenantService.addAggregatorInTenant(tenantId,accountAggregatorSelectedBankDto.getName(),accountAggregatorSelectedBankDto.getSelectedBanks());
		return new ResponseEntity<>("Aggregator added inside Tenant",HttpStatus.CREATED);
	}

}