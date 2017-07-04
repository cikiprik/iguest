/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.service;

import com.iguest.entity.TdEmployee;
import com.iguest.entity.TdRoom;
import com.iguest.entity.TdUser;
import com.iguest.entity.TrJnsEmployee;
import com.iguest.entity.TrJnsRoom;
import com.iguest.entity.TrJnsUser;
import com.iguest.entity.TtRoomRate;
import com.iguest.utils.Enkripsi;
import java.util.Date;
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
    
    public List<TrJnsUser> listJnsUser (){
       return (List<TrJnsUser>) em.createNamedQuery("TrJnsUser.findAll").getResultList();
        
    }
    
    public List<TrJnsRoom> listJnsRoom (){
       return (List<TrJnsRoom>) em.createNamedQuery("TrJnsRoom.findAll").getResultList();
        
    }
    
    public List<TdEmployee> listEmployee (){
       return (List<TdEmployee>) em.createNamedQuery("TdEmployee.findAll").getResultList();
        
    }
    
    public List<TdUser> listUser (){
       return (List<TdUser>) em.createNamedQuery("TdUser.findAll").getResultList();
        
    }
    
    public List<TdRoom> listRoom (){
       return (List<TdRoom>) em.createNamedQuery("TdRoom.findAll").getResultList();
        
    }
    public List<TdEmployee> listEmployeeName (String o){
       return (List<TdEmployee>) em.createNamedQuery("TdEmployee.findByNamaLike").setParameter("nama", "%" + o.toUpperCase() + "%").getResultList();
        
    }
    
    public TdEmployee findEmployee (String o) {
        try {
            return (TdEmployee) em.createNamedQuery("TdEmployee.findByNama").setParameter("nama", o.toUpperCase()).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
        
        
    }
    
    public TdUser findUser (String o) {
        try {
            return (TdUser) em.createNamedQuery("TdUser.findByUsername").setParameter("username", o.toUpperCase()).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
        
        
    }
    
    public TdRoom findRoom (String o) {
        try {
            return (TdRoom) em.createNamedQuery("TdRoom.findByNamaRoom").setParameter("namaRoom", o.toUpperCase()).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
        
        
    }
    
    public TtRoomRate findRoomRate (TdRoom room, Date date) {
        try {
            return (TtRoomRate) em.createNamedQuery("TtRoomRate.findByRoomRate")
                    .setParameter("waktuRate", date)
                    .setParameter("namaRoom", room.getNamaRoom().toUpperCase()).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
        
        
    }
    
    public TtRoomRate findMaxRoomRate (TdRoom room) {
        try {
            return (TtRoomRate) em.createNamedQuery("TtRoomRate.findByMaxRoomRate")
                    .setParameter("Room", room).setFirstResult(0).setMaxResults(1).getSingleResult();
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