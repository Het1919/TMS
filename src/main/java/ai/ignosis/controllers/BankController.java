package ai.ignosis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.ignosis.entities.Bank;
import ai.ignosis.payload.BankGlobalStatusDto;
import ai.ignosis.payload.BankLocalStatusDto;
import ai.ignosis.payload.CreateBankDto;
import ai.ignosis.services.BankService;

@RestController
@RequestMapping("/api")
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping("/banks")
	public ResponseEntity<List<Bank>> getAllBanks() {
		try {
			List<Bank> banks = bankService.getAllBanks();
			return new ResponseEntity<>(banks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/banks/{id}")
	public ResponseEntity<Bank> getBankById(@PathVariable(name="id") int bankId)
	{
		try {
			Bank bank = bankService.getBankById(bankId);
			return new ResponseEntity<>(bank,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/banks")
	public ResponseEntity<String> createBanks(@RequestBody CreateBankDto createBankDto) {
		try {
			bankService.createBanks(createBankDto.getName());
			return new ResponseEntity<>("Banks created", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/global-status")
	public ResponseEntity<String> updateGlobalStatus(@RequestBody BankGlobalStatusDto bankGlobalStatusDto) {
		try {
			System.out.println(bankGlobalStatusDto);
			bankService.updateGlobalStatus(bankGlobalStatusDto.getAggId(), bankGlobalStatusDto.getBankId(),
					bankGlobalStatusDto.getStatus());
			return new ResponseEntity<>("Status updated", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
	}

	@PutMapping("/local-status")
	public ResponseEntity<String> updateLocalStatus(@RequestBody BankLocalStatusDto bankLocalStatusDto) {
		try {
			bankService.updateLocalStatus(bankLocalStatusDto.gettId(), bankLocalStatusDto.getAgId(),
					bankLocalStatusDto.getbId(), bankLocalStatusDto.isStatus());
			return new ResponseEntity<>("Local status updated", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
