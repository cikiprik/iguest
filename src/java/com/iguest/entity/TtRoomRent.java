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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TT_ROOM_RENT", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TtRoomRent.findAll", query = "SELECT t FROM TtRoomRent t"),
    @NamedQuery(name = "TtRoomRent.findByIdRoomRent", query = "SELECT t FROM TtRoomRent t WHERE t.idRoomRent = :idRoomRent"),
    @NamedQuery(name = "TtRoomRent.findByCheckin", query = "SELECT t FROM TtRoomRent t WHERE t.checkin = :checkin"),
    @NamedQuery(name = "TtRoomRent.findByCheckout", query = "SELECT t FROM TtRoomRent t WHERE t.checkout = :checkout"),
    @NamedQuery(name = "TtRoomRent.findByWaktuRekam", query = "SELECT t FROM TtRoomRent t WHERE t.waktuRekam = :waktuRekam")})
public class TtRoomRent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROOM_RENT")
    private Integer idRoomRent;
    @Column(name = "CHECKIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkin;
    @Column(name = "CHECKOUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkout;
    @Column(name = "WAKTU_REKAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuRekam;
    @OneToMany(mappedBy = "idRoomRent")
    private List<TtLogRoom> ttLogRoomList;
    @JoinColumn(name = "ID_GUEST", referencedColumnName = "ID_GUEST")
    @ManyToOne
    private TdGuest idGuest;
    @JoinColumn(name = "ID_PAYMENT", referencedColumnName = "ID_PAYMENT")
    @ManyToOne
    private TtPayment idPayment;

    public TtRoomRent() {
    }

    public TtRoomRent(Integer idRoomRent) {
        this.idRoomRent = idRoomRent;
    }

    public Integer getIdRoomRent() {
        return idRoomRent;
    }

    public void setIdRoomRent(Integer idRoomRent) {
        this.idRoomRent = idRoomRent;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Date getWaktuRekam() {
        return waktuRekam;
    }

    public void setWaktuRekam(Date waktuRekam) {
        this.waktuRekam = waktuRekam;
    }

    public List<TtLogRoom> getTtLogRoomList() {
        return ttLogRoomList;
    }

    public void setTtLogRoomList(List<TtLogRoom> ttLogRoomList) {
        this.ttLogRoomList = ttLogRoomList;
    }

    public TdGuest getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(TdGuest idGuest) {
        this.idGuest = idGuest;
    }

    public TtPayment getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(TtPayment idPayment) {
        this.idPayment = idPayment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoomRent != null ? idRoomRent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TtRoomRent)) {
            return false;
        }
        TtRoomRent other = (TtRoomRent) object;
        if ((this.idRoomRent == null && other.idRoomRent != null) || (this.idRoomRent != null && !this.idRoomRent.equals(other.idRoomRent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TtRoomRent[ idRoomRent=" + idRoomRent + " ]";
    }
    
}
