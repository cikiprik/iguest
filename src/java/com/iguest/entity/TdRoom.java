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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TD_ROOM", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TdRoom.findAll", query = "SELECT t FROM TdRoom t"),
    @NamedQuery(name = "TdRoom.findByIdRoom", query = "SELECT t FROM TdRoom t WHERE t.idRoom = :idRoom"),
    @NamedQuery(name = "TdRoom.findByNamaRoom", query = "SELECT t FROM TdRoom t WHERE UPPER(t.namaRoom) = :namaRoom"),
    @NamedQuery(name = "TdRoom.findByMaxGuest", query = "SELECT t FROM TdRoom t WHERE t.maxGuest = :maxGuest")})
public class TdRoom implements Serializable {

 
    private static final long serialVersionUID = 1L;
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROOM")
    private Integer idRoom;
    @Size(max = 100)
    @Column(name = "NAMA_ROOM")
    private String namaRoom;
    @Column(name = "MAX_GUEST")
    private Integer maxGuest;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @JoinColumn(name = "ID_JNS_ROOM", referencedColumnName = "ID_JNS_ROOM")
    @ManyToOne
    private TrJnsRoom idJnsRoom;
       @OneToMany(mappedBy = "idRoom")
    private List<TtRoomRate> ttRoomRateList;


    public TdRoom() {
    }

    public TdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getNamaRoom() {
        return namaRoom;
    }

    public void setNamaRoom(String namaRoom) {
        this.namaRoom = namaRoom;
    }

    public Integer getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(Integer maxGuest) {
        this.maxGuest = maxGuest;
    }

    public TrJnsRoom getIdJnsRoom() {
        return idJnsRoom;
    }

    public void setIdJnsRoom(TrJnsRoom idJnsRoom) {
        this.idJnsRoom = idJnsRoom;
    }

     public List<TtRoomRate> getTtRoomRateList() {
        return ttRoomRateList;
    }

    public void setTtRoomRateList(List<TtRoomRate> ttRoomRateList) {
        this.ttRoomRateList = ttRoomRateList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoom != null ? idRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdRoom)) {
            return false;
        }
        TdRoom other = (TdRoom) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TdRoom[ idRoom=" + idRoom + " ]";
    }

   
    
}
