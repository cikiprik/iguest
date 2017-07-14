/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.pageCtl;

import com.iguest.entity.TdEmployee;
import com.iguest.entity.TdGuest;
import com.iguest.entity.TdRoom;
import com.iguest.entity.TdUser;
import com.iguest.entity.TrJnsIdentitas;
import com.iguest.entity.TrJnsLogRoom;
import com.iguest.entity.TrJnsUser;
import com.iguest.entity.TtLogRoom;
import com.iguest.entity.TtRoomRate;
import com.iguest.entity.TtRoomRent;
import com.iguest.service.LoadDataBean;
import com.iguest.utils.Enkripsi;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

/**
 *
 * @author dewa
 */
@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class OnlineReserve extends SelectorComposer<Component> {

    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;

    @Wire
    Combobox cmbJnsIdentitas;

    @Wire
    Textbox txtNo, txtNama, txtAlamat, txtKontak, txtEmail, txtNegara;

    @Wire
    Bandbox bdRoom;
    @Wire
    Listbox listBdRoom;

    @Wire
    Label txtRate, lblKet;

    @Wire
    Datebox dbxCheckin, dbxCheckout;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        loadCmbJnsIdentitas(null);
        loadListRoom(null, null);
    }

    @Listen("onChanging=#bdRoom")
    public void clickListRoom(Event event) {
        final Bandbox band = (Bandbox) event.getTarget();
        final org.zkoss.zk.ui.event.InputEvent inputEvent = (org.zkoss.zk.ui.event.InputEvent) event;
        final String initialText = inputEvent.getValue();
        if (initialText.equals("")) {
            loadListRoom(null, null);
        } else {
            loadListRoom(initialText, null);
        }

    }

    @Listen("onSelect=#listBdRoom")
    public void selectListRoom(Event event) {
        TdRoom room = listBdRoom.getSelectedItem().getValue();
        TtRoomRate rate = loadBean.findMaxRoomRate(room);
        System.out.println("rate:" + rate);
        if (rate != null) {
            txtRate.setValue(rate.getRate().toString() + " / night");
        }

    }

    public void hitungCost(TdRoom room) {
        TtRoomRate rate = loadBean.findMaxRoomRate(room);
        Date dateIn = dbxCheckin.getValue();
        Date dateOut = dbxCheckout.getValue();

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

        cal1.setTime(dateIn);
        cal2.setTime(dateOut);

        lblKet.setValue("Total cost " + daysBetween(cal1.getTime(), cal2.getTime()) + " days : " + rate.getRate() * daysBetween(cal1.getTime(), cal2.getTime()));

    }

    @Listen("onChange=#dbxCheckout")
    public void clickdbxCheckout() {
        if (dbxCheckout.getValue() != null) {
            if(listBdRoom.getSelectedItem()!=null){
                hitungCost((TdRoom) listBdRoom.getSelectedItem().getValue());
            }
            
        }

    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public void loadListRoom(String nama, final TdRoom dataLama) {
        if (nama != null) {

            List<TdRoom> data = loadBean.listRoomName(nama);
            listBdRoom.setModel(new ListModelList(data, true));

            listBdRoom.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem lstm, Object t, int i) throws Exception {
                    TdRoom data = (TdRoom) t;

                    if (dataLama != null) {
                        if (data.getIdRoom() == dataLama.getIdRoom()) {
                            listBdRoom.setSelectedItem(lstm);
                        }
                    }

                    Listcell cellJenis = new Listcell();
                    Label lblJenis = new Label();
                    TtRoomRate rate = loadBean.findMaxRoomRate(data);
                    lblJenis.setValue(" (" + data.getIdJnsRoom().getJnsRoom() + "  - " + rate.getRate().toString() + ")");
                    cellJenis.appendChild(lblJenis);
                    lstm.appendChild(cellJenis);

                    lstm.setValue(data);
                    lstm.setLabel(data.getNamaRoom());

                }

            });

        } else {
            List<TdRoom> data = loadBean.listRoom();
            listBdRoom.setModel(new ListModelList(data, true));

            listBdRoom.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem lstm, Object t, int i) throws Exception {
                    TdRoom data = (TdRoom) t;

                    if (dataLama != null) {
                        if (data.getIdRoom() == dataLama.getIdRoom()) {
                            listBdRoom.setSelectedItem(lstm);
                        }
                    }

                    Listcell cellJenis = new Listcell();
                    Label lblJenis = new Label();
                    TtRoomRate rate = loadBean.findMaxRoomRate(data);
                    lblJenis.setValue(" (" + data.getIdJnsRoom().getJnsRoom() + "  - " + rate.getRate().toString() + ")");
                    cellJenis.appendChild(lblJenis);
                    lstm.appendChild(cellJenis);

                    lstm.setValue(data);
                    lstm.setLabel(data.getNamaRoom());

                }

            });
        }

    }

    public void loadCmbJnsIdentitas(TrJnsIdentitas selData) {
        List<TrJnsIdentitas> data = loadBean.listJnsIdentitas();
        cmbJnsIdentitas.setModel(new ListModelList(data, true)); // cmbKota = id zk combobox
        cmbJnsIdentitas.setItemRenderer(new ComboitemRenderer() {
            @Override
            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                TrJnsIdentitas tsk = (TrJnsIdentitas) t;
                cmbtm.setLabel(tsk.getJnsIdentitas());
                cmbtm.setValue(tsk);
               
            }

        });
    }

    public void clear() {
        txtRate.setValue("");
        lblKet.setValue("");
        dbxCheckin.setValue(null);
        dbxCheckout.setValue(null);


        bdRoom.setValue(null);
        listBdRoom.setSelectedItem(null);
        txtAlamat.setValue("");
        txtEmail.setValue("");
        txtKontak.setValue("");
        txtNama.setValue("");
        txtNo.setValue("");
        txtNegara.setValue("");
        cmbJnsIdentitas.setSelectedItem(null);
    }

    @Listen("onClick=#btnSimpan")
    public void simpan() {
        try {

            Messagebox.show("Book rooms?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
                public void onEvent(Event evt) throws InterruptedException {
                    if (evt.getName().equals("onOK")) {
                        // buka window payment

                        TdGuest disimpan = null;

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
                            disimpan = dataLama;
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
                            disimpan = data;
                        }

                        // insert tt room rent
                        TtRoomRent data = new TtRoomRent();
                        data.setCheckin(dbxCheckin.getValue());
                        data.setCheckout(dbxCheckout.getValue());
                        data.setWaktuRekam(new Date());
                        data.setIdGuest(disimpan);

                        loadBean.simpanObject(data);

                        // tt log room
                        TtLogRoom logRoom = new TtLogRoom();
                        // booked
                        logRoom.setIdJnsLogRoom(new TrJnsLogRoom(new Integer("11")));
                        logRoom.setIdRoomRent(data);
                        logRoom.setWaktuLogRoom(new Date());
                        TdRoom room = listBdRoom.getSelectedItem().getValue();
                        TtRoomRate rate = loadBean.findMaxRoomRate(room);
                        logRoom.setIdRoomRate(rate);

                        loadBean.simpanObject(logRoom);
                        
                          alert("Data Tersimpan !");

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        clear();
    }

}
