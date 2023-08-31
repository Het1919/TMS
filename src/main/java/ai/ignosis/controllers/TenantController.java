package ai.ignosis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.entities.Tenant;
import ai.ignosis.entities.TenantAggregatorBank;
import ai.ignosis.payload.AccountAggregatorSelectedBankDto;
import ai.ignosis.payload.TenantDto;
import ai.ignosis.services.TenantAggregatorBankService;
import ai.ignosis.services.TenantService;

@RestController
@RequestMapping("/api")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private TenantAggregatorBankService tenantAggregatorBankService;

	@GetMapping("/tenants")
	public ResponseEntity<List<Tenant>> getAllTenants() {
		try {
			List<Tenant> tenants = tenantService.getAllTenants();
			return new ResponseEntity<>(tenants, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tenants/{id}")
	public ResponseEntity<Tenant> getTenantById(@PathVariable(name="id") int tenantId) {
		try {
			Tenant tenant = tenantService.getTenantById(tenantId);
			return new ResponseEntity<>(tenant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/tenants-aggregators-banks")
	public ResponseEntity<List<TenantAggregatorBank>> getAllTenantsWithAggregatorsAndBanks()
	{
		try {
			List<TenantAggregatorBank> tenantsWithAggregators = tenantAggregatorBankService.getAllTenantsWithAggregatorsAndBanks();
			return new ResponseEntity<>(tenantsWithAggregators,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/tenants")
	public ResponseEntity<String> addTenant(@RequestBody TenantDto tenantDto) {
		try {
			tenantService.saveTenant(tenantDto.getName());
			return new ResponseEntity<>("Tenant Created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/tenants-aggregators/{id}")
	public ResponseEntity<String> addAggregatorInTenant(@PathVariable(name = "id") int tenantId,
			@RequestBody AccountAggregatorSelectedBankDto accountAggregatorSelectedBankDto) {
		try {
			tenantService.addAggregatorInTenant(tenantId, accountAggregatorSelectedBankDto.getName(),
					accountAggregatorSelectedBankDto.getSelectedBanks());
			return new ResponseEntity<>("Aggregator added inside Tenant", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}