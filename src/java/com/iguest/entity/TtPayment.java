/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dewa
 */
@Entity
@Table(name = "TT_PAYMENT", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TtPayment.findAll", query = "SELECT t FROM TtPayment t"),
    @NamedQuery(name = "TtPayment.findByIdPayment", query = "SELECT t FROM TtPayment t WHERE t.idPayment = :idPayment"),
    @NamedQuery(name = "TtPayment.findByTotal", query = "SELECT t FROM TtPayment t WHERE t.total = :total"),
    @NamedQuery(name = "TtPayment.findByWaktuPayment", query = "SELECT t FROM TtPayment t WHERE t.waktuPayment = :waktuPayment"),
    @NamedQuery(name = "TtPayment.findByDisc", query = "SELECT t FROM TtPayment t WHERE t.disc = :disc")})
public class TtPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAYMENT")
    private Integer idPayment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private Float total;
    @Column(name = "WAKTU_PAYMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPayment;
    @Column(name = "DISC")
    private Float disc;
    @JoinColumn(name = "ID_JNS_PAYMENT", referencedColumnName = "ID_JNS_PAYMENT")
    @ManyToOne
    private TrJnsPayment idJnsPayment;
    @JoinColumn(name = "ID_PAYMENT_STATUS", referencedColumnName = "ID_PAYMENT_STATUS")
    @ManyToOne
    private TrPaymentStatus idPaymentStatus;
    @OneToMany(mappedBy = "idPayment")
    private List<TtRoomRent> ttRoomRentList;

    public TtPayment() {
    }

    public TtPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getWaktuPayment() {
        return waktuPayment;
    }

    public void setWaktuPayment(Date waktuPayment) {
        this.waktuPayment = waktuPayment;
    }

    public Float getDisc() {
        return disc;
    }

    public void setDisc(Float disc) {
        this.disc = disc;
    }

    public TrJnsPayment getIdJnsPayment() {
        return idJnsPayment;
    }

    public void setIdJnsPayment(TrJnsPayment idJnsPayment) {
        this.idJnsPayment = idJnsPayment;
    }

    public TrPaymentStatus getIdPaymentStatus() {
        return idPaymentStatus;
    }

    public void setIdPaymentStatus(TrPaymentStatus idPaymentStatus) {
        this.idPaymentStatus = idPaymentStatus;
    }

    public List<TtRoomRent> getTtRoomRentList() {
        return ttRoomRentList;
    }

    public void setTtRoomRentList(List<TtRoomRent> ttRoomRentList) {
        this.ttRoomRentList = ttRoomRentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPayment != null ? idPayment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TtPayment)) {
            return false;
        }
        TtPayment other = (TtPayment) object;
        if ((this.idPayment == null && other.idPayment != null) || (this.idPayment != null && !this.idPayment.equals(other.idPayment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TtPayment[ idPayment=" + idPayment + " ]";
    }
    
}
