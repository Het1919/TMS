package ai.ignosis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.ignosis.entities.TenantAggregatorBank;

@Repository
public interface TenantAggregatorBankRepository extends JpaRepository<TenantAggregatorBank, Integer>{
	
	
}
