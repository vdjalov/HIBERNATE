package app.ccb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.ccb.domain.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
   
	Client findByFullName(String fullName);
	
	
	@Query(value = "select c.full_name, c.age, count(cards.bank_account_id) as number_of_cards, cards.bank_account_id \n " + 
                   "from clients as c \n" +
                   "join bank_accounts as ba \n" +
				   "on c.id = ba.client_id \n" +
				   "join cards \n" + 
				   "on ba.id = cards.bank_account_id \n" +
				   "group by cards.bank_account_id \n" +
				   "order by number_of_cards desc limit 1;", nativeQuery = true)
	List<Object[]> findAndPrintTheCLientWithMostCards();
	
	
	
}
