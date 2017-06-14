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
@Table(name = "TR_JNS_LOG_SISTEM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsLogSistem.findAll", query = "SELECT t FROM TrJnsLogSistem t"),
    @NamedQuery(name = "TrJnsLogSistem.findByIdJnsLogSistem", query = "SELECT t FROM TrJnsLogSistem t WHERE t.idJnsLogSistem = :idJnsLogSistem"),
    @NamedQuery(name = "TrJnsLogSistem.findByJnsLogSistem", query = "SELECT t FROM TrJnsLogSistem t WHERE t.jnsLogSistem = :jnsLogSistem")})
public class TrJnsLogSistem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_LOG_SISTEM")
    private Integer idJnsLogSistem;
    @Size(max = 50)
    @Column(name = "JNS_LOG_SISTEM")
    private String jnsLogSistem;

    public TrJnsLogSistem() {
    }

    public TrJnsLogSistem(Integer idJnsLogSistem) {
        this.idJnsLogSistem = idJnsLogSistem;
    }

    public Integer getIdJnsLogSistem() {
        return idJnsLogSistem;
    }

    public void setIdJnsLogSistem(Integer idJnsLogSistem) {
        this.idJnsLogSistem = idJnsLogSistem;
    }

    public String getJnsLogSistem() {
        return jnsLogSistem;
    }

    public void setJnsLogSistem(String jnsLogSistem) {
        this.jnsLogSistem = jnsLogSistem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsLogSistem != null ? idJnsLogSistem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsLogSistem)) {
            return false;
        }
        TrJnsLogSistem other = (TrJnsLogSistem) object;
        if ((this.idJnsLogSistem == null && other.idJnsLogSistem != null) || (this.idJnsLogSistem != null && !this.idJnsLogSistem.equals(other.idJnsLogSistem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsLogSistem[ idJnsLogSistem=" + idJnsLogSistem + " ]";
    }
    
}
