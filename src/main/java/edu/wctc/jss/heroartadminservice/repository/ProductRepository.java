package edu.wctc.jss.heroartadminservice.repository;

import edu.wctc.jss.heroartadminservice.entity.Product;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Integer>, Serializable{
    
}
