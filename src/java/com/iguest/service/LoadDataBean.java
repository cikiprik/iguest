/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.service;

import com.iguest.entity.TdEmployee;
import com.iguest.entity.TdUser;
import com.iguest.entity.TrJnsEmployee;
import com.iguest.utils.Enkripsi;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dewa
 */
@Named("LoadDataBean")
@Stateless
public class LoadDataBean {
    @PersistenceContext(unitName = "iGuestPU")
    EntityManager em;
    
    public List<TrJnsEmployee> listJnsEmployee (){
       return (List<TrJnsEmployee>) em.createNamedQuery("TrJnsEmployee.findAll").getResultList();
        
    }
    
    public List<TdEmployee> listEmployee (){
       return (List<TdEmployee>) em.createNamedQuery("TdEmployee.findAll").getResultList();
        
    }
    
    public TdEmployee findEmployee (String o) {
        try {
            return (TdEmployee) em.createNamedQuery("TdEmployee.findByNama").setParameter("nama", o.toUpperCase()).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
        
        
    }
    
    public Object simpanObject (Object o){
        em.persist(o);
        em.flush();
        return o;
    }
    
    public Object ubahObject (Object o){
        em.merge(o);
        em.flush();
        return o;
    }
    
    public void hapusObject (Object o){
        em.remove(em.merge(o));
        em.flush();
    }
}
