package mostwanted.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mostwanted.domain.entities.Racer;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer>{

	Racer findByName(String name);
	
	@Query(value = "select r.name, r.age, c.brand, c.model, c.year_of_production, \n" +  
				   "(select count(cc.brand) \n" +
				   "from cars as cc \n" +
				   "where cc.racer_id = c.racer_id) as count_of_cars \n" +
				   "from racers as r \n" +
				   "join cars as c \n" +
				   "on c.racer_id = r.id \n" +
				   "where r.age = 0 \n" +
				   "order by count_of_cars desc, r.name;", nativeQuery = true)
	List<Object[]> racingCarsThatHaveOwnersWithAgeNull();
}
