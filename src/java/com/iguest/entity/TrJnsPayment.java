/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dewa
 */
@Entity
@Table(name = "TR_JNS_PAYMENT", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsPayment.findAll", query = "SELECT t FROM TrJnsPayment t"),
    @NamedQuery(name = "TrJnsPayment.findByIdJnsPayment", query = "SELECT t FROM TrJnsPayment t WHERE t.idJnsPayment = :idJnsPayment"),
    @NamedQuery(name = "TrJnsPayment.findByJnsPayment", query = "SELECT t FROM TrJnsPayment t WHERE t.jnsPayment = :jnsPayment")})
public class TrJnsPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_PAYMENT")
    private Integer idJnsPayment;
    @Size(max = 50)
    @Column(name = "JNS_PAYMENT")
    private String jnsPayment;

    public TrJnsPayment() {
    }

    public TrJnsPayment(Integer idJnsPayment) {
        this.idJnsPayment = idJnsPayment;
    }

    public Integer getIdJnsPayment() {
        return idJnsPayment;
    }

    public void setIdJnsPayment(Integer idJnsPayment) {
        this.idJnsPayment = idJnsPayment;
    }

    public String getJnsPayment() {
        return jnsPayment;
    }

    public void setJnsPayment(String jnsPayment) {
        this.jnsPayment = jnsPayment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsPayment != null ? idJnsPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsPayment)) {
            return false;
        }
        TrJnsPayment other = (TrJnsPayment) object;
        if ((this.idJnsPayment == null && other.idJnsPayment != null) || (this.idJnsPayment != null && !this.idJnsPayment.equals(other.idJnsPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsPayment[ idJnsPayment=" + idJnsPayment + " ]";
    }
    
}
