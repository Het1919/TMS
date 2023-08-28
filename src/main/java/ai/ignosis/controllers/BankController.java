package ai.ignosis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.dto.BankGlobalStatusDto;
import ai.ignosis.dto.BankLocalStatusDto;
import ai.ignosis.dto.CreateBankDto;
import ai.ignosis.entities.Bank;
import ai.ignosis.services.BankService;

@RestController
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@GetMapping("/api/banks")
	public ResponseEntity<List<Bank>> getAllBanks()
	{
		List<Bank> banks = bankService.getAllBanks();
		return new ResponseEntity<>(banks,HttpStatus.OK);
	}

	@PostMapping("/api/banks")
	public ResponseEntity<String> createBanks(@RequestBody CreateBankDto createBankDto)
	{
		bankService.createBanks(createBankDto.getName());
		return new ResponseEntity<>("Banks created",HttpStatus.CREATED); 
	}
	
	@PutMapping("/api/global-status")
	public ResponseEntity<String> updateGlobalStatus(@RequestBody BankGlobalStatusDto bankGlobalStatusDto)
	{
		bankService.updateGlobalStatus(bankGlobalStatusDto.getAggId(),bankGlobalStatusDto.getBankId(),bankGlobalStatusDto.getStatus());
		return new ResponseEntity<>("Status updated",HttpStatus.OK);
	}
	
	@PutMapping("/api/local-status")
	public ResponseEntity<String> updateLocalStatus(@RequestBody BankLocalStatusDto bankLocalStatusDto)
	{
		bankService.updateLocalStatus(bankLocalStatusDto.gettId(),bankLocalStatusDto.getAgId(),bankLocalStatusDto.getbId(),bankLocalStatusDto.isStatus());
		return new ResponseEntity<>("Local status updated",HttpStatus.OK);
	}

}
