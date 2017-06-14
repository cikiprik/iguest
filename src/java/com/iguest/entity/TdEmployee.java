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
@Table(name = "TD_EMPLOYEE", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TdEmployee.findAll", query = "SELECT t FROM TdEmployee t"),
    @NamedQuery(name = "TdEmployee.findByIdEmployee", query = "SELECT t FROM TdEmployee t WHERE t.idEmployee = :idEmployee"),
    @NamedQuery(name = "TdEmployee.findByIdJnsEmployee", query = "SELECT t FROM TdEmployee t WHERE t.idJnsEmployee = :idJnsEmployee"),
    @NamedQuery(name = "TdEmployee.findByNama", query = "SELECT t FROM TdEmployee t WHERE t.nama = :nama"),
    @NamedQuery(name = "TdEmployee.findByAlamat", query = "SELECT t FROM TdEmployee t WHERE t.alamat = :alamat"),
    @NamedQuery(name = "TdEmployee.findByHp", query = "SELECT t FROM TdEmployee t WHERE t.hp = :hp"),
    @NamedQuery(name = "TdEmployee.findByEmail", query = "SELECT t FROM TdEmployee t WHERE t.email = :email")})
public class TdEmployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_EMPLOYEE")
    private Integer idEmployee;
    @Column(name = "ID_JNS_EMPLOYEE")
    private Integer idJnsEmployee;
    @Size(max = 50)
    @Column(name = "NAMA")
    private String nama;
    @Size(max = 250)
    @Column(name = "ALAMAT")
    private String alamat;
    @Size(max = 15)
    @Column(name = "HP")
    private String hp;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;

    public TdEmployee() {
    }

    public TdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdJnsEmployee() {
        return idJnsEmployee;
    }

    public void setIdJnsEmployee(Integer idJnsEmployee) {
        this.idJnsEmployee = idJnsEmployee;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmployee != null ? idEmployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdEmployee)) {
            return false;
        }
        TdEmployee other = (TdEmployee) object;
        if ((this.idEmployee == null && other.idEmployee != null) || (this.idEmployee != null && !this.idEmployee.equals(other.idEmployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TdEmployee[ idEmployee=" + idEmployee + " ]";
    }
    
}
