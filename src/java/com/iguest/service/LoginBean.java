/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.service;

import com.iguest.entity.TdUser;
import com.iguest.utils.Enkripsi;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dewa
 */
@Named("LoginBean")
@Stateless
public class LoginBean {
    @PersistenceContext(unitName = "iGuestPU")
    EntityManager em;
    
    public TdUser loginCheck(String username, String password){
        try {
           Enkripsi e = new Enkripsi();
        String hash = e.sha256(password);
        TdUser tdUser= null;
        tdUser = (TdUser) em.createNamedQuery("TdUser.findByUsernameAndPass").setParameter("username", username).setParameter("password", hash).setFirstResult(0).setMaxResults(1).getSingleResult();
        if(tdUser!=null){
            // ada user
            
            return tdUser;
        } else {
            return null;
        } 
        } catch (Exception e) {
            return null;
        }
        
        
        
    }
}
