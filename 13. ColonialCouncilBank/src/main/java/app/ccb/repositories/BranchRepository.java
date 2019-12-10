package app.ccb.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.ccb.domain.entities.Branch;

@Repository
public interface BranchRepository extends JpaRepository <Branch, Integer>{
   
	Optional <Branch> findByName(String name);
}
