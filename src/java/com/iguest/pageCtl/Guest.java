/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.pageCtl;

import com.iguest.entity.TdEmployee;
import com.iguest.entity.TdGuest;
import com.iguest.entity.TdUser;
import com.iguest.entity.TrJnsIdentitas;
import com.iguest.entity.TrJnsUser;
import com.iguest.service.LoadDataBean;
import com.iguest.utils.Enkripsi;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

/**
 *
 * @author dewa
 */
@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class Guest extends SelectorComposer<Component> {
    
    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;
    
    @Wire
    Combobox cmbJnsIdentitas;
    
    @Wire
    Listbox lbData;
    
    @Wire    
    Textbox txtNo, txtNama, txtAlamat, txtKontak, txtEmail, txtNegara;
    
    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        loadCmbJnsIdentitas(null);
        loadList();
    }
    
    public void loadList() {
        List<TdGuest> data = loadBean.listGuest();
        lbData.setModel(new ListModelList(data, true));
        
        lbData.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem lstm, Object t, int i) throws Exception {
                TdGuest data = (TdGuest) t;
                
                Listcell cellNama = new Listcell();
                Label lblNama = new Label();
                lblNama.setValue(data.getNama());
                cellNama.appendChild(lblNama);
                lstm.appendChild(cellNama);
                
                Listcell cellNo = new Listcell();
                Label lblNo = new Label();
                lblNo.setValue(data.getNoIdentitas());
                cellNo.appendChild(lblNo);
                lstm.appendChild(cellNo);
                
                Listcell cellJns = new Listcell();
                Label lblJns = new Label();
                lblJns.setValue(data.getIdJnsIdentitas().getJnsIdentitas());
                cellJns.appendChild(lblJns);
                lstm.appendChild(cellJns);
                
                Listcell cellkontak = new Listcell();
                Label lblkontak = new Label();
                lblkontak.setValue(data.getKontak());
                cellkontak.appendChild(lblkontak);
                lstm.appendChild(cellkontak);
                
                Listcell cellEmail = new Listcell();
                Label lblEmail = new Label();
                lblEmail.setValue(data.getEmail());
                cellEmail.appendChild(lblEmail);
                lstm.appendChild(cellEmail);
                
                lstm.setValue(data);
                
            }
            
        });
    }
    
    public void loadCmbJnsIdentitas(final TrJnsIdentitas selData) {
        List<TrJnsIdentitas> data = loadBean.listJnsIdentitas();
        cmbJnsIdentitas.setModel(new ListModelList(data, true)); // cmbKota = id zk combobox
        cmbJnsIdentitas.setItemRenderer(new ComboitemRenderer() {
            @Override
            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                TrJnsIdentitas tsk = (TrJnsIdentitas) t;
                cmbtm.setLabel(tsk.getJnsIdentitas());
                cmbtm.setValue(tsk);
                // set selected item
                if (selData != null) {
                    if (tsk.getIdJnsIdentitas() == selData.getIdJnsIdentitas()) {
                        cmbJnsIdentitas.setSelectedItem(cmbtm);
                    }
                }
            }
            
        });
    }
    
    @Listen("onClick=#btnSimpan")
    public void simpan() {
        try {
            TdGuest dataLama = loadBean.findGuest(txtNo.getValue());
            
            if (dataLama != null) {
                dataLama.setIdJnsIdentitas((TrJnsIdentitas) cmbJnsIdentitas.getSelectedItem().getValue());
                dataLama.setAlamat(txtAlamat.getValue());
                dataLama.setEmail(txtEmail.getValue());
                dataLama.setKontak(txtKontak.getValue());
                dataLama.setNama(txtNama.getValue());
                dataLama.setNoIdentitas(txtNo.getValue());
                dataLama.setWargaNegara(txtNegara.getValue());
                
                loadBean.ubahObject(dataLama);
            } else {
                TdGuest data = new TdGuest();
                data.setIdJnsIdentitas((TrJnsIdentitas) cmbJnsIdentitas.getSelectedItem().getValue());
                data.setAlamat(txtAlamat.getValue());
                data.setEmail(txtEmail.getValue());
                data.setKontak(txtKontak.getValue());
                data.setNama(txtNama.getValue());
                data.setNoIdentitas(txtNo.getValue());
                data.setWargaNegara(txtNegara.getValue());
                
                loadBean.simpanObject(data);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        clear();
        loadList();
    }
    
    public void clear() {
        txtAlamat.setValue("");
        txtEmail.setValue("");
        txtKontak.setValue("");
        txtNama.setValue("");
        txtNo.setValue("");
        txtNegara.setValue("");
        cmbJnsIdentitas.setSelectedItem(null);
    }
    
    @Listen("onClick=#btnTambah")
    public void tambah() {
        clear();
    }
    
    @Listen("onClick=#btnHapus")
    public void hapus() {
        if (lbData.getSelectedItem() != null) {
            TdGuest data = lbData.getSelectedItem().getValue();
            if (data != null) {
                // hapus
                loadBean.hapusObject(data);
                clear();
                loadList();
            }
        } else {
            // belum dipilih
        }
    }
    
    
    @Listen("onClick=#lbData")
    public void clickList() {
        if (lbData.getSelectedItem() != null) {
            TdGuest data = lbData.getSelectedItem().getValue();
            txtAlamat.setValue(data.getAlamat());
            txtEmail.setValue(data.getEmail());
            txtKontak.setValue(data.getKontak());
            txtNama.setValue(data.getNama());
            txtNegara.setValue(data.getWargaNegara());
            txtNo.setValue(data.getNoIdentitas());
            loadCmbJnsIdentitas(data.getIdJnsIdentitas());


        }

    }
}
