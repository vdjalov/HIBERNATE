package hibernateCodeFirstExercises.GringottsDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args ) {
        
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringottsDB");
    	EntityManager em = emf.createEntityManager();
    			
    	Deposit deposit = new Deposit();
    	//deposit.setLastName("Dzhalov");
    	deposit.setAge(-5);
    
    	em.getTransaction().begin();
    	em.persist(deposit);
    	
    }
}
