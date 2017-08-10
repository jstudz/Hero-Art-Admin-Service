package edu.wctc.jss.heroartadminservice.repository;

import edu.wctc.jss.heroartadminservice.entity.Distributor;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "distributors", path = "distributors")
public interface DistributorRepository extends JpaRepository<Distributor, Integer>, Serializable{
    
    @Query("SELECT d FROM Distributor d LEFT JOIN FETCH d.productSet WHERE d.distributorId = (:id)")
    public Distributor findByIdAndFetchProductsEagerly(@Param("id") Integer id);
    
}