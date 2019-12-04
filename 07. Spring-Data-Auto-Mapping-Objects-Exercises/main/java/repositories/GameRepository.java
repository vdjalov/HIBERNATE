package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entities.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, Long>{
	public Game findByTitle(String title);
	
}
