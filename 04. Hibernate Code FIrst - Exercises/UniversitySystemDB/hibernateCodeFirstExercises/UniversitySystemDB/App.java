package hibernateCodeFirstExercises.UniversitySystemDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args ) {
        
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("UniveristySystemDB");
    	EntityManager em = emf.createEntityManager();
    			
    
    	
    }
}
