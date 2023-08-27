package ai.ignosis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ai.ignosis.entities.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {

}
