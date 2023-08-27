package ai.ignosis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.dto.AccountAggregatorDto;
import ai.ignosis.services.AccountAggregatorService;

@RestController
@RequestMapping("/api")
public class AccountAggregatorController {
	
	@Autowired
	private AccountAggregatorService accountAggregatorService;

	@PostMapping("/aggregators")
	public ResponseEntity<String> addAggregator(@RequestBody AccountAggregatorDto aggregator) {
		accountAggregatorService.addAggregator(aggregator.getName(),aggregator.getBanks());		
		return new ResponseEntity<>("Aggregator added successfully",HttpStatus.CREATED);
	}
	
}
