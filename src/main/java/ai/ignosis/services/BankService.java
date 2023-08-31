package ai.ignosis.services;

import java.util.*;

import ai.ignosis.entities.Bank;

public interface BankService {

	public List<Bank> getAllBanks();

	public List<Bank> getBankByName(String name) ;

	public void updateGlobalStatus(int aggId, int bankId, boolean status);

	public void createBanks(String name);

	public void updateLocalStatus(int tId, int agId, int bId, boolean status);

	public Bank getBankById(int id);
}
