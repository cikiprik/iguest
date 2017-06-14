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
@Table(name = "TD_GUEST", catalog = "db_iguest", schema = "")
@NamedQueries({
    @NamedQuery(name = "TdGuest.findAll", query = "SELECT t FROM TdGuest t"),
    @NamedQuery(name = "TdGuest.findByIdGuest", query = "SELECT t FROM TdGuest t WHERE t.idGuest = :idGuest"),
    @NamedQuery(name = "TdGuest.findByIdJnsIdentitas", query = "SELECT t FROM TdGuest t WHERE t.idJnsIdentitas = :idJnsIdentitas"),
    @NamedQuery(name = "TdGuest.findByNama", query = "SELECT t FROM TdGuest t WHERE t.nama = :nama"),
    @NamedQuery(name = "TdGuest.findByNoIdentitas", query = "SELECT t FROM TdGuest t WHERE t.noIdentitas = :noIdentitas"),
    @NamedQuery(name = "TdGuest.findByAlamat", query = "SELECT t FROM TdGuest t WHERE t.alamat = :alamat"),
    @NamedQuery(name = "TdGuest.findByWargaNegara", query = "SELECT t FROM TdGuest t WHERE t.wargaNegara = :wargaNegara"),
    @NamedQuery(name = "TdGuest.findByKontak", query = "SELECT t FROM TdGuest t WHERE t.kontak = :kontak"),
    @NamedQuery(name = "TdGuest.findByEmail", query = "SELECT t FROM TdGuest t WHERE t.email = :email")})
public class TdGuest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_GUEST")
    private Integer idGuest;
    @Column(name = "ID_JNS_IDENTITAS")
    private Integer idJnsIdentitas;
    @Size(max = 50)
    @Column(name = "NAMA")
    private String nama;
    @Size(max = 25)
    @Column(name = "NO_IDENTITAS")
    private String noIdentitas;
    @Size(max = 250)
    @Column(name = "ALAMAT")
    private String alamat;
    @Size(max = 10)
    @Column(name = "WARGA_NEGARA")
    private String wargaNegara;
    @Size(max = 15)
    @Column(name = "KONTAK")
    private String kontak;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "EMAIL")
    private String email;

    public TdGuest() {
    }

    public TdGuest(Integer idGuest) {
        this.idGuest = idGuest;
    }

    public Integer getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Integer idGuest) {
        this.idGuest = idGuest;
    }

    public Integer getIdJnsIdentitas() {
        return idJnsIdentitas;
    }

    public void setIdJnsIdentitas(Integer idJnsIdentitas) {
        this.idJnsIdentitas = idJnsIdentitas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(String wargaNegara) {
        this.wargaNegara = wargaNegara;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuest != null ? idGuest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TdGuest)) {
            return false;
        }
        TdGuest other = (TdGuest) object;
        if ((this.idGuest == null && other.idGuest != null) || (this.idGuest != null && !this.idGuest.equals(other.idGuest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iguest.entity.TdGuest[ idGuest=" + idGuest + " ]";
    }
    
}
