package hibernateCodeFirstExercises.paymentSystemDB;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args ) {
        
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PaymentSystemDB");
    	EntityManager em = emf.createEntityManager();
    			
    
    	User user = new User("Jelqzko", "Zdravkov", "JelqzkoZdravkov@abv.bg", "12345");
    
    	CreditCard cc = new CreditCard();
    	cc.setOwner(user);
    	cc.setNumber(new BigInteger("2255663399774411"));
    	cc.setCardType("VISA");
    	cc.setExpirationMonth("05");
    	cc.setExpirationYear("2023");
    	
    	
    	em.getTransaction().begin();
    	em.persist(cc);
    	em.persist(user);
    	em.getTransaction().commit();
    	
    	User userOne = new User("Minko", "Praznikov", "mpraznik@abv.bg", "12345");
    	BigInteger baNumber = new BigInteger("12121255588447");
    	BankAccount ba = new BankAccount(baNumber, userOne, "OBB", "22558899");
    	ba.setOwner(userOne);
    	em.getTransaction().begin();
    	em.persist(userOne);
    	em.persist(ba);
    	em.getTransaction().commit();
    }
}


















