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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TT_LOG_ROOM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TtLogRoom.findAll", query = "SELECT t FROM TtLogRoom t"),
    @NamedQuery(name = "TtLogRoom.findByIdRoomRent", query = "SELECT t FROM TtLogRoom t WHERE t.idRoomRent = :idRoomRent and t.idJnsLogRoom = :idJnsLog order by t.idLogRoom desc"),
    @NamedQuery(name = "TtLogRoom.findByIdLogRoom", query = "SELECT t FROM TtLogRoom t WHERE t.idLogRoom = :idLogRoom"),
    @NamedQuery(name = "TtLogRoom.findByWaktuLogRoom", query = "SELECT t FROM TtLogRoom t WHERE t.waktuLogRoom = :waktuLogRoom")})
public class TtLogRoom implements Serializable {

  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOG_ROOM")
    private Integer idLogRoom;
    @Column(name = "WAKTU_LOG_ROOM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuLogRoom;
    @JoinColumn(name = "ID_JNS_LOG_ROOM", referencedColumnName = "ID_JNS_LOG_ROOM")
    @ManyToOne
    private TrJnsLogRoom idJnsLogRoom;
    
    @JoinColumn(name = "ID_ROOM_RENT", referencedColumnName = "ID_ROOM_RENT")
    @ManyToOne
    private TtRoomRent idRoomRent;
      @JoinColumn(name = "ID_ROOM_RATE", referencedColumnName = "ID_ROOM_RATE")
    @ManyToOne
    private TtRoomRate idRoomRate;


    public TtLogRoom() {
    }

    public TtLogRoom(Integer idLogRoom) {
        this.idLogRoom = idLogRoom;
    }

    public Integer getIdLogRoom() {
        return idLogRoom;
    }

    public void setIdLogRoom(Integer idLogRoom) {
        this.idLogRoom = idLogRoom;
    }

    public Date getWaktuLogRoom() {
        return waktuLogRoom;
    }

    public void setWaktuLogRoom(Date waktuLogRoom) {
        this.waktuLogRoom = waktuLogRoom;
    }

    public TrJnsLogRoom getIdJnsLogRoom() {
        return idJnsLogRoom;
    }

    public void setIdJnsLogRoom(TrJnsLogRoom idJnsLogRoom) {
        this.idJnsLogRoom = idJnsLogRoom;
    }

   

    public TtRoomRent getIdRoomRent() {
        return idRoomRent;
    }

    public void setIdRoomRent(TtRoomRent idRoomRent) {
        this.idRoomRent = idRoomRent;
    }
    
      public TtRoomRate getIdRoomRate() {
        return idRoomRate;
    }

    public void setIdRoomRate(TtRoomRate idRoomRate) {
        this.idRoomRate = idRoomRate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogRoom != null ? idLogRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TtLogRoom)) {
            return false;
        }
        TtLogRoom other = (TtLogRoom) object;
        if ((this.idLogRoom == null && other.idLogRoom != null) || (this.idLogRoom != null && !this.idLogRoom.equals(other.idLogRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TtLogRoom[ idLogRoom=" + idLogRoom + " ]";
    }

  
    
}
