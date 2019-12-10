package app.ccb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.ccb.domain.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
    Employee findByFirstNameAndLastName(String firstName, String lastName);
    
    @Query(value = "select e.first_name, e.last_name, count(ec.client_id) as clients_count \n" + 
    			   "from employees as e \n" +
    			   "join employees_clients as ec \n" +
    			   "on e.id = ec.employee_id \n" +
    			   "group by ec.employee_id \n" +
    			   "order by clients_count desc, e.id;", nativeQuery = true)
    List<Object[]> employeesByNumberOfClients();
}
