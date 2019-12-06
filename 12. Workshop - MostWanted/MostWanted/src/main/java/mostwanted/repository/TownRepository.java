package mostwanted.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mostwanted.domain.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer>{
  
	Town findByName(String name);
	
	@Query(value = "select t.name, \n" + 
                   "(select count(rr.town_id) \n" +
                   "from racers as rr \n" +
                   "where rr.town_id = t.id) as racers_count \n" +
                   "from towns as t \n" +
                   "join racers as r \n" +
                   "on r.town_id = t.id \n" +
                   "group by t.name \n" +
				   "order by racers_count desc, t.name;", nativeQuery = true)
	List<Object[]> racingTowns();
}
