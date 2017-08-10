package edu.wctc.jss.heroartadminservice.service;

import edu.wctc.jss.heroartadminservice.entity.Distributor;
import edu.wctc.jss.heroartadminservice.repository.DistributorRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("distributorService")
@Transactional(readOnly = true)
public class DistributorService {
    private transient final Logger LOG = LoggerFactory.getLogger(DistributorService.class);
    
    @Autowired
    private DistributorRepository distRepo;
    
    public DistributorService()
    {
        
    }
    
    public List<Distributor> findAll()
    {
        return distRepo.findAll();
    }
    
    public Distributor findByIdAndFetchProductsEagerly(String id){
        return distRepo.findByIdAndFetchProductsEagerly(new Integer(id));
    }
    
    public Distributor findById(String id)
    {
        return distRepo.findOne(new Integer(id));
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void remove(Distributor distributor)
    {
        distRepo.delete(distributor);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void edit(Distributor distributor)
    {
        distRepo.saveAndFlush(distributor);
    }
}
