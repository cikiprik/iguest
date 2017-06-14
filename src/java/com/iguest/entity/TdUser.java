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
@Table(name = "TD_USER", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TdUser.findAll", query = "SELECT t FROM TdUser t"),
    @NamedQuery(name = "TdUser.findByIdUser", query = "SELECT t FROM TdUser t WHERE t.idUser = :idUser"),
    @NamedQuery(name = "TdUser.findByIdEmployee", query = "SELECT t FROM TdUser t WHERE t.idEmployee = :idEmployee"),
    @NamedQuery(name = "TdUser.findByIdJnsUser", query = "SELECT t FROM TdUser t WHERE t.idJnsUser = :idJnsUser"),
    @NamedQuery(name = "TdUser.findByFlagAktif", query = "SELECT t FROM TdUser t WHERE t.flagAktif = :flagAktif"),
    @NamedQuery(name = "TdUser.findByUsernameAndPass", query = "SELECT t FROM TdUser t WHERE t.username = :username and t.password = :password "),
    @NamedQuery(name = "TdUser.findByUsername", query = "SELECT t FROM TdUser t WHERE t.username = :username"),
    @NamedQuery(name = "TdUser.findByPassword", query = "SELECT t FROM TdUser t WHERE t.password = :password")})
public class TdUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private Integer idUser;
    @Column(name = "ID_EMPLOYEE")
    private Integer idEmployee;
    @Column(name = "ID_JNS_USER")
    private Integer idJnsUser;
    @Column(name = "FLAG_AKTIF")
    private Boolean flagAktif;
    @Size(max = 64)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 64)
    @Column(name = "PASSWORD")
    private String password;

    public TdUser() {
    }

    public TdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdJnsUser() {
        return idJnsUser;
    }

    public void setIdJnsUser(Integer idJnsUser) {
        this.idJnsUser = idJnsUser;
    }

    public Boolean getFlagAktif() {
        return flagAktif;
    }

    public void setFlagAktif(Boolean flagAktif) {
        this.flagAktif = flagAktif;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdUser)) {
            return false;
        }
        TdUser other = (TdUser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TdUser[ idUser=" + idUser + " ]";
    }
    
}
