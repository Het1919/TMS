package ai.ignosis.services;

import org.springframework.stereotype.Service;

import ai.ignosis.entities.Bank;
import java.util.*;

@Service
public class BankService {
	
	
	public List<Bank> banks;
	
	public BankService(){
		banks = new ArrayList<>();
		banks.add(new Bank(1,"SBI",true,true));
		banks.add(new Bank(2,"HDFC",true,true));
		banks.add(new Bank(3,"BOB",true,true));
		banks.add(new Bank(4,"PNB",true,true));
		banks.add(new Bank(5,"ICICI",true,true));
		System.out.println("Hello");
	}
	
	public List<Bank> getBanks()
	{
		return banks;
	}
	
	public Bank getBankByName(String name)
	{
		for(Bank b : banks)
		{
			if(b.getBankName().equals(name))
			{
				return b;
			}
		}
		return null;
	}
	
	
}
