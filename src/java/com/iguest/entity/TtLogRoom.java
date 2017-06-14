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
@Table(name = "TT_LOG_ROOM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TtLogRoom.findAll", query = "SELECT t FROM TtLogRoom t"),
    @NamedQuery(name = "TtLogRoom.findByIdLogRoom", query = "SELECT t FROM TtLogRoom t WHERE t.idLogRoom = :idLogRoom"),
    @NamedQuery(name = "TtLogRoom.findByIdJnsLogRoom", query = "SELECT t FROM TtLogRoom t WHERE t.idJnsLogRoom = :idJnsLogRoom"),
    @NamedQuery(name = "TtLogRoom.findByIdRoom", query = "SELECT t FROM TtLogRoom t WHERE t.idRoom = :idRoom"),
    @NamedQuery(name = "TtLogRoom.findByIdRoomRent", query = "SELECT t FROM TtLogRoom t WHERE t.idRoomRent = :idRoomRent"),
    @NamedQuery(name = "TtLogRoom.findByWaktuLogRoom", query = "SELECT t FROM TtLogRoom t WHERE t.waktuLogRoom = :waktuLogRoom")})
public class TtLogRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LOG_ROOM")
    private Integer idLogRoom;
    @Column(name = "ID_JNS_LOG_ROOM")
    private Integer idJnsLogRoom;
    @Column(name = "ID_ROOM")
    private Integer idRoom;
    @Column(name = "ID_ROOM_RENT")
    private Integer idRoomRent;
    @Column(name = "WAKTU_LOG_ROOM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuLogRoom;

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

    public Integer getIdJnsLogRoom() {
        return idJnsLogRoom;
    }

    public void setIdJnsLogRoom(Integer idJnsLogRoom) {
        this.idJnsLogRoom = idJnsLogRoom;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdRoomRent() {
        return idRoomRent;
    }

    public void setIdRoomRent(Integer idRoomRent) {
        this.idRoomRent = idRoomRent;
    }

    public Date getWaktuLogRoom() {
        return waktuLogRoom;
    }

    public void setWaktuLogRoom(Date waktuLogRoom) {
        this.waktuLogRoom = waktuLogRoom;
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
