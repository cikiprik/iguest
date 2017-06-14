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
@Table(name = "TR_JNS_LOG_ROOM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsLogRoom.findAll", query = "SELECT t FROM TrJnsLogRoom t"),
    @NamedQuery(name = "TrJnsLogRoom.findByIdJnsLogRoom", query = "SELECT t FROM TrJnsLogRoom t WHERE t.idJnsLogRoom = :idJnsLogRoom"),
    @NamedQuery(name = "TrJnsLogRoom.findByJnsLogRoom", query = "SELECT t FROM TrJnsLogRoom t WHERE t.jnsLogRoom = :jnsLogRoom")})
public class TrJnsLogRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_LOG_ROOM")
    private Integer idJnsLogRoom;
    @Size(max = 50)
    @Column(name = "JNS_LOG_ROOM")
    private String jnsLogRoom;

    public TrJnsLogRoom() {
    }

    public TrJnsLogRoom(Integer idJnsLogRoom) {
        this.idJnsLogRoom = idJnsLogRoom;
    }

    public Integer getIdJnsLogRoom() {
        return idJnsLogRoom;
    }

    public void setIdJnsLogRoom(Integer idJnsLogRoom) {
        this.idJnsLogRoom = idJnsLogRoom;
    }

    public String getJnsLogRoom() {
        return jnsLogRoom;
    }

    public void setJnsLogRoom(String jnsLogRoom) {
        this.jnsLogRoom = jnsLogRoom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsLogRoom != null ? idJnsLogRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsLogRoom)) {
            return false;
        }
        TrJnsLogRoom other = (TrJnsLogRoom) object;
        if ((this.idJnsLogRoom == null && other.idJnsLogRoom != null) || (this.idJnsLogRoom != null && !this.idJnsLogRoom.equals(other.idJnsLogRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsLogRoom[ idJnsLogRoom=" + idJnsLogRoom + " ]";
    }
    
}
