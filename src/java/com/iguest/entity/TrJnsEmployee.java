/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dewa
 */
@Entity
@Table(name = "TR_JNS_EMPLOYEE", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsEmployee.findAll", query = "SELECT t FROM TrJnsEmployee t"),
    @NamedQuery(name = "TrJnsEmployee.findByIdJnsEmployee", query = "SELECT t FROM TrJnsEmployee t WHERE t.idJnsEmployee = :idJnsEmployee"),
    @NamedQuery(name = "TrJnsEmployee.findByJnsEmployee", query = "SELECT t FROM TrJnsEmployee t WHERE t.jnsEmployee = :jnsEmployee")})
public class TrJnsEmployee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_EMPLOYEE")
    private Integer idJnsEmployee;
    @Size(max = 50)
    @Column(name = "JNS_EMPLOYEE")
    private String jnsEmployee;
    @OneToMany(mappedBy = "idJnsEmployee")
    private List<TdEmployee> tdEmployeeList;

    public TrJnsEmployee() {
    }

    public TrJnsEmployee(Integer idJnsEmployee) {
        this.idJnsEmployee = idJnsEmployee;
    }

    public Integer getIdJnsEmployee() {
        return idJnsEmployee;
    }

    public void setIdJnsEmployee(Integer idJnsEmployee) {
        this.idJnsEmployee = idJnsEmployee;
    }

    public String getJnsEmployee() {
        return jnsEmployee;
    }

    public void setJnsEmployee(String jnsEmployee) {
        this.jnsEmployee = jnsEmployee;
    }

    public List<TdEmployee> getTdEmployeeList() {
        return tdEmployeeList;
    }

    public void setTdEmployeeList(List<TdEmployee> tdEmployeeList) {
        this.tdEmployeeList = tdEmployeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsEmployee != null ? idJnsEmployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsEmployee)) {
            return false;
        }
        TrJnsEmployee other = (TrJnsEmployee) object;
        if ((this.idJnsEmployee == null && other.idJnsEmployee != null) || (this.idJnsEmployee != null && !this.idJnsEmployee.equals(other.idJnsEmployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsEmployee[ idJnsEmployee=" + idJnsEmployee + " ]";
    }
    
}
