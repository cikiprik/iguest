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
@Table(name = "TR_PAYMENT_STATUS", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrPaymentStatus.findAll", query = "SELECT t FROM TrPaymentStatus t"),
    @NamedQuery(name = "TrPaymentStatus.findByIdPaymentStatus", query = "SELECT t FROM TrPaymentStatus t WHERE t.idPaymentStatus = :idPaymentStatus"),
    @NamedQuery(name = "TrPaymentStatus.findByPaymentStatus", query = "SELECT t FROM TrPaymentStatus t WHERE t.paymentStatus = :paymentStatus")})
public class TrPaymentStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAYMENT_STATUS")
    private Integer idPaymentStatus;
    @Size(max = 50)
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
    @OneToMany(mappedBy = "idPaymentStatus")
    private List<TtPayment> ttPaymentList;

    public TrPaymentStatus() {
    }

    public TrPaymentStatus(Integer idPaymentStatus) {
        this.idPaymentStatus = idPaymentStatus;
    }

    public Integer getIdPaymentStatus() {
        return idPaymentStatus;
    }

    public void setIdPaymentStatus(Integer idPaymentStatus) {
        this.idPaymentStatus = idPaymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<TtPayment> getTtPaymentList() {
        return ttPaymentList;
    }

    public void setTtPaymentList(List<TtPayment> ttPaymentList) {
        this.ttPaymentList = ttPaymentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaymentStatus != null ? idPaymentStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrPaymentStatus)) {
            return false;
        }
        TrPaymentStatus other = (TrPaymentStatus) object;
        if ((this.idPaymentStatus == null && other.idPaymentStatus != null) || (this.idPaymentStatus != null && !this.idPaymentStatus.equals(other.idPaymentStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrPaymentStatus[ idPaymentStatus=" + idPaymentStatus + " ]";
    }
    
}
