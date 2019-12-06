package mostwanted.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mostwanted.domain.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository <District, Integer>{
   
	District findByName(String districtName);
}
