package app.ccb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.ccb.domain.entities.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{
	
	BankAccount findByAccountNumber(String accountNumber);
   
}
