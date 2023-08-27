package ai.ignosis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.ignosis.entities.AccountAggregatorBanks;

@Repository
public interface AccountAggregatorBankRepository extends JpaRepository<AccountAggregatorBanks, Integer>{

}
