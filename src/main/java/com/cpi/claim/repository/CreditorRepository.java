package com.cpi.claim.repository;

import com.cpi.claim.domain.Creditor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Creditor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreditorRepository extends JpaRepository<Creditor, Long>, JpaSpecificationExecutor<Creditor> {

}
