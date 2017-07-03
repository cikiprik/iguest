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
@Table(name = "TR_JNS_IDENTITAS", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsIdentitas.findAll", query = "SELECT t FROM TrJnsIdentitas t"),
    @NamedQuery(name = "TrJnsIdentitas.findByIdJnsIdentitas", query = "SELECT t FROM TrJnsIdentitas t WHERE t.idJnsIdentitas = :idJnsIdentitas"),
    @NamedQuery(name = "TrJnsIdentitas.findByJnsIdentitas", query = "SELECT t FROM TrJnsIdentitas t WHERE t.jnsIdentitas = :jnsIdentitas")})
public class TrJnsIdentitas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_IDENTITAS")
    private Integer idJnsIdentitas;
    @Size(max = 50)
    @Column(name = "JNS_IDENTITAS")
    private String jnsIdentitas;
    @OneToMany(mappedBy = "idJnsIdentitas")
    private List<TdGuest> tdGuestList;

    public TrJnsIdentitas() {
    }

    public TrJnsIdentitas(Integer idJnsIdentitas) {
        this.idJnsIdentitas = idJnsIdentitas;
    }

    public Integer getIdJnsIdentitas() {
        return idJnsIdentitas;
    }

    public void setIdJnsIdentitas(Integer idJnsIdentitas) {
        this.idJnsIdentitas = idJnsIdentitas;
    }

    public String getJnsIdentitas() {
        return jnsIdentitas;
    }

    public void setJnsIdentitas(String jnsIdentitas) {
        this.jnsIdentitas = jnsIdentitas;
    }

    public List<TdGuest> getTdGuestList() {
        return tdGuestList;
    }

    public void setTdGuestList(List<TdGuest> tdGuestList) {
        this.tdGuestList = tdGuestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsIdentitas != null ? idJnsIdentitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsIdentitas)) {
            return false;
        }
        TrJnsIdentitas other = (TrJnsIdentitas) object;
        if ((this.idJnsIdentitas == null && other.idJnsIdentitas != null) || (this.idJnsIdentitas != null && !this.idJnsIdentitas.equals(other.idJnsIdentitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsIdentitas[ idJnsIdentitas=" + idJnsIdentitas + " ]";
    }
    
}
