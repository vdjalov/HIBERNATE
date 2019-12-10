package app.ccb.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.ccb.domain.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
    
	
	@Query(value = "select c.card_number, c.card_status \n" +
				   "from cards as c \n" +
				   "where c.bank_account_id = :bankAccount", nativeQuery = true)
	List<Object[]> cardsByBankAccountId(@Param("bankAccount")int bankAccount);
	
}
