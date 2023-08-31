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

import ai.ignosis.entities.AccountAggregator;
import ai.ignosis.entities.AccountAggregatorBanks;
import ai.ignosis.payload.AccountAggregatorDto;
import ai.ignosis.services.AccountAggregatorBankService;
import ai.ignosis.services.AccountAggregatorService;

@RestController
@RequestMapping("/api")
public class AccountAggregatorController {

	@Autowired
	private AccountAggregatorService accountAggregatorService;
	
	@Autowired
	private AccountAggregatorBankService accountAggregatorBankService;

	@GetMapping("/aggregators")
	public ResponseEntity<List<AccountAggregator>> getAllAccountAggregators() {
		try {
			List<AccountAggregator> aggregators = accountAggregatorService.getAllAccountAggregators();
			return new ResponseEntity<>(aggregators, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/aggregators/{id}")
	public ResponseEntity<AccountAggregator> getAggregatorById(@PathVariable(name="id") int id) {
		try {
			AccountAggregator aggregator = accountAggregatorService.getAggregatorById(id);
			return new ResponseEntity<>(aggregator, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/aggregators-banks")
	public ResponseEntity<List<AccountAggregatorBanks>> getAllAccountAggregatorsWithBanks(){
		try {
			List<AccountAggregatorBanks> aggWithBanks = accountAggregatorBankService.getAllAccountAggregatorsWithBanks();
			return new ResponseEntity<>(aggWithBanks,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/aggregators")
	public ResponseEntity<String> addAggregator(@RequestBody AccountAggregatorDto aggregator) {
		try {
			accountAggregatorService.addAggregator(aggregator.getName(), aggregator.getBanks());
			return new ResponseEntity<>("Aggregator added successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
