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
@Table(name = "TR_JNS_USER", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsUser.findAll", query = "SELECT t FROM TrJnsUser t"),
    @NamedQuery(name = "TrJnsUser.findByIdJnsUser", query = "SELECT t FROM TrJnsUser t WHERE t.idJnsUser = :idJnsUser"),
    @NamedQuery(name = "TrJnsUser.findByJnsUser", query = "SELECT t FROM TrJnsUser t WHERE t.jnsUser = :jnsUser")})
public class TrJnsUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_USER")
    private Integer idJnsUser;
    @Size(max = 50)
    @Column(name = "JNS_USER")
    private String jnsUser;

    public TrJnsUser() {
    }

    public TrJnsUser(Integer idJnsUser) {
        this.idJnsUser = idJnsUser;
    }

    public Integer getIdJnsUser() {
        return idJnsUser;
    }

    public void setIdJnsUser(Integer idJnsUser) {
        this.idJnsUser = idJnsUser;
    }

    public String getJnsUser() {
        return jnsUser;
    }

    public void setJnsUser(String jnsUser) {
        this.jnsUser = jnsUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsUser != null ? idJnsUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsUser)) {
            return false;
        }
        TrJnsUser other = (TrJnsUser) object;
        if ((this.idJnsUser == null && other.idJnsUser != null) || (this.idJnsUser != null && !this.idJnsUser.equals(other.idJnsUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsUser[ idJnsUser=" + idJnsUser + " ]";
    }
    
}
