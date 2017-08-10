
package edu.wctc.jss.heroartadminservice.service;

import edu.wctc.jss.heroartadminservice.entity.Product;
import edu.wctc.jss.heroartadminservice.repository.ProductRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;


@Repository("productService")
@Transactional(readOnly = true)
public class ProductService {
    
     private transient final Logger LOG = LoggerFactory.getLogger(ProductService.class);
     
     @Autowired
     private ProductRepository productRepo;
     
     public ProductService() {
         
     }
     
     public List<Product> findAll() {
         return productRepo.findAll();
     }
     
     public Product findById(String id){
         return productRepo.findOne(new Integer(id));
     }
     
     @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
     public void remove(Product product) {
         productRepo.delete(product);
     }
     
     @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
     public void edit(Product product){
         productRepo.saveAndFlush(product);
     }
     
}
