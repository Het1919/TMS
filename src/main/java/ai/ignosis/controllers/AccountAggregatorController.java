package ai.ignosis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.dto.AccountAggregatorDto;
import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.services.AccountAggregatorService;

@RestController
public class AccountAggregatorController {
	
	@Autowired
	private AccountAggregatorService accountAggregatorService;
	
	@GetMapping("/api/aggregators")
	public ResponseEntity<List<AccountAggregator>> getAllAccountAggregators()
	{
		List<AccountAggregator> aggregators = accountAggregatorService.getAllAccountAggregators();
		return new ResponseEntity<>(aggregators,HttpStatus.OK);
	}

	@PostMapping("/api/aggregators")
	public ResponseEntity<String> addAggregator(@RequestBody AccountAggregatorDto aggregator) {
		accountAggregatorService.addAggregator(aggregator.getName(),aggregator.getBanks());		
		return new ResponseEntity<>("Aggregator added successfully",HttpStatus.CREATED);
	}
	
}
