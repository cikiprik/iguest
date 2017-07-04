/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.pageCtl;

import com.iguest.entity.TdRoom;
import com.iguest.entity.TrJnsRoom;
import com.iguest.entity.TrJnsUser;
import com.iguest.entity.TtRoomRate;
import com.iguest.service.LoadDataBean;
import java.util.Date;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
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
public class Rooms extends SelectorComposer<Component> {

    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;

    @Wire
    Combobox cmbJnsRoom;

    @Wire
    Listbox lbData;

    @Wire
    Textbox txtNamaRoom, txtMax, txtPrice;

    @Wire
    Datebox dbxRate;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        loadCmbJnsRoom(null);
        loadList();
        dbxRate.setValue(new Date());

    }

    public void loadCmbJnsRoom(final TrJnsRoom selData) {
        List<TrJnsRoom> data = loadBean.listJnsRoom();
        cmbJnsRoom.setModel(new ListModelList(data, true)); // cmbKota = id zk combobox
        cmbJnsRoom.setItemRenderer(new ComboitemRenderer() {
            @Override
            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                TrJnsRoom tsk = (TrJnsRoom) t;
                cmbtm.setLabel(tsk.getJnsRoom());
                cmbtm.setValue(tsk);
                // set selected item
                if (selData != null) {
                    if (tsk.getIdJnsRoom() == selData.getIdJnsRoom()) {
                        cmbJnsRoom.setSelectedItem(cmbtm);
                    }
                }
            }

        });

    }

    @Listen("onClick=#btnSimpan")
    public void simpan() {
        try {
            TdRoom dataLama = loadBean.findRoom(txtNamaRoom.getValue());

            if (dataLama != null) {
                dataLama.setIdJnsRoom((TrJnsRoom) cmbJnsRoom.getSelectedItem().getValue());
                dataLama.setMaxGuest(Integer.valueOf(txtMax.getValue()));
                dataLama.setNamaRoom(txtNamaRoom.getValue());
                loadBean.ubahObject(dataLama);

                simpanRate(dbxRate.getValue(), Float.valueOf(txtPrice.getValue()), dataLama);

            } else {
                TdRoom data = new TdRoom();
                data.setIdJnsRoom((TrJnsRoom) cmbJnsRoom.getSelectedItem().getValue());
                data.setMaxGuest(Integer.valueOf(txtMax.getValue()));
                data.setNamaRoom(txtNamaRoom.getValue());
                loadBean.simpanObject(data);

                simpanRate(dbxRate.getValue(), Float.valueOf(txtPrice.getValue()), data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        clear();
        loadList();
    }
    
     @Listen("onClick=#btnTambah")
    public void tambah() {
        clear();
    }
    
    @Listen("onClick=#btnHapus")
    public void hapus() {
        if (lbData.getSelectedItem() != null) {
            TdRoom data = lbData.getSelectedItem().getValue();
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
            TdRoom data = lbData.getSelectedItem().getValue();
            txtMax.setValue(data.getMaxGuest().toString());
            txtNamaRoom.setValue(data.getNamaRoom());
            TtRoomRate roomRate = loadBean.findMaxRoomRate(data);
            if(roomRate!=null){
                txtPrice.setValue(roomRate.getRate().toString());
            dbxRate.setValue(roomRate.getWaktuRate());
            }
            
            loadCmbJnsRoom(data.getIdJnsRoom());


        }

    }
    
    public void clear(){
        txtMax.setValue("");
        txtNamaRoom.setValue("");
        txtPrice.setValue("");
        cmbJnsRoom.setSelectedItem(null);
    }

    public void simpanRate(Date date, Float rate, TdRoom room) {
        TtRoomRate roomRate = loadBean.findRoomRate(room, date);
        if (roomRate != null) {
            roomRate.setRate(rate);
            loadBean.ubahObject(roomRate);
        } else {
            TtRoomRate roomRateBaru = new TtRoomRate();
            roomRateBaru.setIdRoom(room);
            roomRateBaru.setWaktuRate(date);
            roomRateBaru.setRate(rate);
            loadBean.simpanObject(roomRateBaru);
        }
    }

    public void loadList() {
        List<TdRoom> data = loadBean.listRoom();
        lbData.setModel(new ListModelList(data, true));

        lbData.setItemRenderer(new ListitemRenderer() {

            @Override
            public void render(Listitem lstm, Object t, int i) throws Exception {
                TdRoom data = (TdRoom) t;

                Listcell cellJnsRoom = new Listcell();
                Label lblJnsRoom = new Label();
                lblJnsRoom.setValue(data.getIdJnsRoom().getJnsRoom());
                cellJnsRoom.appendChild(lblJnsRoom);
                lstm.appendChild(cellJnsRoom);

                Listcell cellNama = new Listcell();
                Label lblNama = new Label();
                lblNama.setValue(data.getNamaRoom());
                cellNama.appendChild(lblNama);
                lstm.appendChild(cellNama);

                Listcell cellJns = new Listcell();
                Label lblJns = new Label();
                lblJns.setValue(data.getMaxGuest().toString());
                cellJns.appendChild(lblJns);
                lstm.appendChild(cellJns);

                Listcell cellRate = new Listcell();
                Label lblRate = new Label();
                TtRoomRate roomRate = loadBean.findMaxRoomRate(data);
                if(roomRate!=null){
                    lblRate.setValue(roomRate.getRate().toString() + " ("+roomRate.getWaktuRate()+")");
                } else {
                    lblRate.setValue("-");
                }
                
                cellRate.appendChild(lblRate);
                lstm.appendChild(cellRate);

                lstm.setValue(data);
            }

        });
    }

}
