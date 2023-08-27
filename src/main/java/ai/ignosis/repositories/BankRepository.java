package ai.ignosis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.ignosis.entities.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer>{

	public List<Bank> findByBankName(String bankName);
	
}
