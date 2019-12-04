package XMLParseCarDealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import XMLParseCarDealer.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
