package ai.ignosis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.dto.BankGlobalStatusDto;
import ai.ignosis.dto.BankLocalStatusDao;
import ai.ignosis.dto.CreateBankDto;
import ai.ignosis.services.BankService;

@RestController
@RequestMapping("/api")
public class BankController {
	
	@Autowired
	private BankService bankService;
	
	@PutMapping("/globalstatus")
	public ResponseEntity<String> updateGlobalStatus(@RequestBody BankGlobalStatusDto bankGlobalStatusDto)
	{
		bankService.updateGlobalStatus(bankGlobalStatusDto.getAggId(),bankGlobalStatusDto.getBankId(),bankGlobalStatusDto.getStatus());
		return new ResponseEntity<>("Status updated",HttpStatus.OK);
	}
	
	@PostMapping("/banks")
	public ResponseEntity<String> createBanks(@RequestBody CreateBankDto createBankDto)
	{
		bankService.createBanks(createBankDto.getName());
		return new ResponseEntity<>("Banks created",HttpStatus.CREATED); 
	}
	
	@PutMapping("/localstatus")
	public ResponseEntity<String> updateLocalStatus(@RequestBody BankLocalStatusDao bankLocalStatusDao)
	{
		bankService.updateLocalStatus(bankLocalStatusDao.gettId(),bankLocalStatusDao.getAgId(),bankLocalStatusDao.getbId(),bankLocalStatusDao.isStatus());
		return new ResponseEntity<>("Local status updated",HttpStatus.OK);
	}

}
