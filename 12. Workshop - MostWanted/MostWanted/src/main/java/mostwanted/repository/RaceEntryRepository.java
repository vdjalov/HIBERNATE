package mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mostwanted.domain.entities.RaceEntry;

@Repository
public interface RaceEntryRepository  extends JpaRepository< RaceEntry , Integer>{
   
}
