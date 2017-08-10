/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jss.heroartadminservice.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jamie
 */
@Entity
@Table(name = "distributor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributor.findAll", query = "SELECT d FROM Distributor d"),
    @NamedQuery(name = "Distributor.findByDistributorId", query = "SELECT d FROM Distributor d WHERE d.distributorId = :distributorId"),
    @NamedQuery(name = "Distributor.findByDistributorName", query = "SELECT d FROM Distributor d WHERE d.distributorName = :distributorName"),
    @NamedQuery(name = "Distributor.findByCity", query = "SELECT d FROM Distributor d WHERE d.city = :city"),
    @NamedQuery(name = "Distributor.findByState", query = "SELECT d FROM Distributor d WHERE d.state = :state")})
public class Distributor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "distributor_id")
    private Integer distributorId;
    @Size(max = 90)
    @Column(name = "distributor_name")
    private String distributorName;
    @Size(max = 90)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "state")
    private String state;
    @OneToMany(mappedBy = "distributorId")
    private Set<Product> productSet;

    public Distributor() {
    }

    public Distributor(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlTransient
    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (distributorId != null ? distributorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributor)) {
            return false;
        }
        Distributor other = (Distributor) object;
        if ((this.distributorId == null && other.distributorId != null) || (this.distributorId != null && !this.distributorId.equals(other.distributorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.jss.heroartadminservice.entity.Distributor[ distributorId=" + distributorId + " ]";
    }
    
}
