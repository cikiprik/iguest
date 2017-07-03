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
@Table(name = "TR_JNS_ROOM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TrJnsRoom.findAll", query = "SELECT t FROM TrJnsRoom t"),
    @NamedQuery(name = "TrJnsRoom.findByIdJnsRoom", query = "SELECT t FROM TrJnsRoom t WHERE t.idJnsRoom = :idJnsRoom"),
    @NamedQuery(name = "TrJnsRoom.findByJnsRoom", query = "SELECT t FROM TrJnsRoom t WHERE t.jnsRoom = :jnsRoom")})
public class TrJnsRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JNS_ROOM")
    private Integer idJnsRoom;
    @Size(max = 50)
    @Column(name = "JNS_ROOM")
    private String jnsRoom;
    @OneToMany(mappedBy = "idJnsRoom")
    private List<TdRoom> tdRoomList;

    public TrJnsRoom() {
    }

    public TrJnsRoom(Integer idJnsRoom) {
        this.idJnsRoom = idJnsRoom;
    }

    public Integer getIdJnsRoom() {
        return idJnsRoom;
    }

    public void setIdJnsRoom(Integer idJnsRoom) {
        this.idJnsRoom = idJnsRoom;
    }

    public String getJnsRoom() {
        return jnsRoom;
    }

    public void setJnsRoom(String jnsRoom) {
        this.jnsRoom = jnsRoom;
    }

    public List<TdRoom> getTdRoomList() {
        return tdRoomList;
    }

    public void setTdRoomList(List<TdRoom> tdRoomList) {
        this.tdRoomList = tdRoomList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJnsRoom != null ? idJnsRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrJnsRoom)) {
            return false;
        }
        TrJnsRoom other = (TrJnsRoom) object;
        if ((this.idJnsRoom == null && other.idJnsRoom != null) || (this.idJnsRoom != null && !this.idJnsRoom.equals(other.idJnsRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TrJnsRoom[ idJnsRoom=" + idJnsRoom + " ]";
    }
    
}
