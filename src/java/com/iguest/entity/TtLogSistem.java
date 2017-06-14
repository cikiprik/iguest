/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dewa
 */
@Entity
@Table(name = "TT_LOG_SISTEM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TtLogSistem.findAll", query = "SELECT t FROM TtLogSistem t"),
    @NamedQuery(name = "TtLogSistem.findByIdLogSistem", query = "SELECT t FROM TtLogSistem t WHERE t.idLogSistem = :idLogSistem"),
    @NamedQuery(name = "TtLogSistem.findByIdUser", query = "SELECT t FROM TtLogSistem t WHERE t.idUser = :idUser"),
    @NamedQuery(name = "TtLogSistem.findByIdJnsLogSistem", query = "SELECT t FROM TtLogSistem t WHERE t.idJnsLogSistem = :idJnsLogSistem"),
    @NamedQuery(name = "TtLogSistem.findByWaktuLog", query = "SELECT t FROM TtLogSistem t WHERE t.waktuLog = :waktuLog")})
public class TtLogSistem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOG_SISTEM")
    private Integer idLogSistem;
    @Column(name = "ID_USER")
    private Integer idUser;
    @Column(name = "ID_JNS_LOG_SISTEM")
    private Integer idJnsLogSistem;
    @Column(name = "WAKTU_LOG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuLog;

    public TtLogSistem() {
    }

    public TtLogSistem(Integer idLogSistem) {
        this.idLogSistem = idLogSistem;
    }

    public Integer getIdLogSistem() {
        return idLogSistem;
    }

    public void setIdLogSistem(Integer idLogSistem) {
        this.idLogSistem = idLogSistem;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdJnsLogSistem() {
        return idJnsLogSistem;
    }

    public void setIdJnsLogSistem(Integer idJnsLogSistem) {
        this.idJnsLogSistem = idJnsLogSistem;
    }

    public Date getWaktuLog() {
        return waktuLog;
    }

    public void setWaktuLog(Date waktuLog) {
        this.waktuLog = waktuLog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogSistem != null ? idLogSistem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TtLogSistem)) {
            return false;
        }
        TtLogSistem other = (TtLogSistem) object;
        if ((this.idLogSistem == null && other.idLogSistem != null) || (this.idLogSistem != null && !this.idLogSistem.equals(other.idLogSistem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TtLogSistem[ idLogSistem=" + idLogSistem + " ]";
    }
    
}
