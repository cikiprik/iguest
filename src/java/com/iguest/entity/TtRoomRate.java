/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.entity;

import com.iguest.entity.TdRoom;
import com.iguest.entity.TtLogRoom;
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
@Table(name = "TT_ROOM_RATE", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TtRoomRate.findAll", query = "SELECT t FROM TtRoomRate t"),
    @NamedQuery(name = "TtRoomRate.findByIdRoomRate", query = "SELECT t FROM TtRoomRate t WHERE t.idRoomRate = :idRoomRate"),
    @NamedQuery(name = "TtRoomRate.findByRoomRate", query = "SELECT t FROM TtRoomRate t WHERE UPPER(t.idRoom.namaRoom) = :namaRoom and t.waktuRate  = :waktuRate "),
    @NamedQuery(name = "TtRoomRate.findByMaxRoomRate", query = "SELECT t FROM TtRoomRate t WHERE t.idRoom = :Room ORDER BY t.waktuRate desc"),
    @NamedQuery(name = "TtRoomRate.findByRate", query = "SELECT t FROM TtRoomRate t WHERE t.rate = :rate"),
    @NamedQuery(name = "TtRoomRate.findByWaktuRate", query = "SELECT t FROM TtRoomRate t WHERE t.waktuRate = :waktuRate")})
public class TtRoomRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROOM_RATE")
    private Integer idRoomRate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RATE")
    private Float rate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WAKTU_RATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuRate;
    @OneToMany(mappedBy = "idRoomRate")
    private List<TtLogRoom> ttLogRoomList;
    @JoinColumn(name = "ID_ROOM", referencedColumnName = "ID_ROOM")
    @ManyToOne
    private TdRoom idRoom;

    public TtRoomRate() {
    }

    public TtRoomRate(Integer idRoomRate) {
        this.idRoomRate = idRoomRate;
    }

    public TtRoomRate(Integer idRoomRate, Date waktuRate) {
        this.idRoomRate = idRoomRate;
        this.waktuRate = waktuRate;
    }

    public Integer getIdRoomRate() {
        return idRoomRate;
    }

    public void setIdRoomRate(Integer idRoomRate) {
        this.idRoomRate = idRoomRate;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Date getWaktuRate() {
        return waktuRate;
    }

    public void setWaktuRate(Date waktuRate) {
        this.waktuRate = waktuRate;
    }

    public List<TtLogRoom> getTtLogRoomList() {
        return ttLogRoomList;
    }

    public void setTtLogRoomList(List<TtLogRoom> ttLogRoomList) {
        this.ttLogRoomList = ttLogRoomList;
    }

    public TdRoom getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(TdRoom idRoom) {
        this.idRoom = idRoom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoomRate != null ? idRoomRate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TtRoomRate)) {
            return false;
        }
        TtRoomRate other = (TtRoomRate) object;
        if ((this.idRoomRate == null && other.idRoomRate != null) || (this.idRoomRate != null && !this.idRoomRate.equals(other.idRoomRate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entt.TtRoomRate[ idRoomRate=" + idRoomRate + " ]";
    }
    
}
