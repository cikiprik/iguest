/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.pageCtl;

import com.iguest.entity.TdRoom;
import com.iguest.entity.TrJnsLogRoom;
import com.iguest.entity.TrJnsPayment;
import com.iguest.entity.TrPaymentStatus;
import com.iguest.entity.TtLogRoom;
import com.iguest.entity.TtPayment;
import com.iguest.entity.TtRoomRate;
import com.iguest.entity.TtRoomRent;
import com.iguest.service.LoadDataBean;
import com.iguest.utils.CalendarEventUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
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
public class Checkout extends SelectorComposer<Component> {

    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;

    @Wire
    Combobox cmbFilter;

    @Wire
    Textbox txtDataFilter;

    @Wire
    Listbox lbData;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        try {
            cmbFilter.setSelectedIndex(0);
            loadData(null, null);
        } catch (Exception e) {
        }

    }

    @Listen("onClick=#btnCari")
    public void cari() {
        loadData(txtDataFilter.getValue(), (String) cmbFilter.getSelectedItem().getValue());
    }

    public void loadData(String dataFilter, String filterBy) {
        try {
            List<TtRoomRent> data = null;
            if ((dataFilter == null || dataFilter.equals(""))) {
                data = loadBean.listRoomRent();
            } else {

                System.out.println("cari");
                // cari
                if (filterBy.equals("0")) {
                    // nama
                    data = loadBean.listRoomRentByNama(dataFilter);
                }
                if (filterBy.equals("1")) {
                    // identitas
                    data = loadBean.listRoomRentByID(dataFilter);
                }
            }
            if (data != null) {

                final List<TrJnsPayment> dataPembayaran = loadBean.listJnsPayment();
                final List<TrPaymentStatus> dataPembayaranStatus = loadBean.listJnsPaymentStatus();

                lbData.setModel(new ListModelList(data, true));

                lbData.setItemRenderer(new ListitemRenderer() {
                    @Override
                    public void render(Listitem lstm, Object t, int i) throws Exception {
                        final TtRoomRent data = (TtRoomRent) t;

                        TtLogRoom logRoomMax = loadBean.findLogRoomMax(data);
                        if (!logRoomMax.getIdJnsLogRoom().getIdJnsLogRoom().toString().equals("1")) {
                            lstm.detach();
                        }

                        Listcell cellNama = new Listcell();
                        Label lblNama = new Label();
                        lblNama.setValue(data.getIdGuest().getNama());
                        cellNama.appendChild(lblNama);
                        lstm.appendChild(cellNama);

                        Listcell cellden = new Listcell();
                        Label lblIden = new Label();
                        lblIden.setValue(data.getIdGuest().getNoIdentitas() + " - " + data.getIdGuest().getIdJnsIdentitas().getJnsIdentitas());
                        cellden.appendChild(lblIden);
                        lstm.appendChild(cellden);

                        Listcell cellJns = new Listcell();
                        Label lblJns = new Label();

                        TtLogRoom ttLogRoom = loadBean.findLogRoom(data, new TrJnsLogRoom(new Integer("11")));
                        lblJns.setValue(ttLogRoom.getIdRoomRate().getIdRoom().getNamaRoom());
                        cellJns.appendChild(lblJns);
                        lstm.appendChild(cellJns);

                        Listcell cellIn = new Listcell();
                        Label lblIn = new Label();
                        lblIn.setValue(sdf.format(data.getCheckin()));
                        cellIn.appendChild(lblIn);
                        lstm.appendChild(cellIn);

                        Listcell cellOut = new Listcell();
                        Label lblOut = new Label();
                        lblOut.setValue(sdf.format(data.getCheckout()));
                        cellOut.appendChild(lblOut);
                        lstm.appendChild(cellOut);

                        Listcell cellStatus = new Listcell();

                        final Button btnIn = new Button();
                        btnIn.setLabel("Checkout");
                        btnIn.setDisabled(true);
                        if (data.getIdPayment() != null) {
                            btnIn.setDisabled(false);
                        }

                        btnIn.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                            @Override
                            public void onEvent(Event t) throws Exception {
                                Messagebox.show("Check out guest?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
                                    public void onEvent(Event evt) throws InterruptedException {
                                        if (evt.getName().equals("onOK")) {
                                            try {
                                                TtRoomRent simpan = data;
                                                // tt log room
                                                TtLogRoom logRoom = new TtLogRoom();
                                                // booked
                                                logRoom.setIdJnsLogRoom(new TrJnsLogRoom(new Integer("2")));
                                                logRoom.setIdRoomRent(simpan);
                                                logRoom.setWaktuLogRoom(new Date());
                                                TdRoom room = simpan.getTtLogRoomList().get(0).getIdRoomRate().getIdRoom();
                                                TtRoomRate rate = loadBean.findMaxRoomRate(room);
                                                logRoom.setIdRoomRate(rate);

                                                loadBean.simpanObject(logRoom);

                                                loadData(null, null);
                                                alert("Data Tersimpan !");
                                            } catch (Exception e) {
                                            }

                                        }
                                    }
                                });
                            }

                        });
                        final Combobox cbxJnsPembayaran = new Combobox();

                        cbxJnsPembayaran.setModel(new ListModelList(dataPembayaran, true)); // cmbKota = id zk combobox
                        cbxJnsPembayaran.setItemRenderer(new ComboitemRenderer() {
                            @Override
                            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                                try {
                                    TrJnsPayment tsk = (TrJnsPayment) t;
                                    cmbtm.setLabel(tsk.getJnsPayment());
                                    cmbtm.setValue(tsk);
                                    // set selected item
                                    if (data.getIdPayment().getIdJnsPayment() != null) {
                                        if (data.getIdPayment().getIdJnsPayment().getIdJnsPayment() == tsk.getIdJnsPayment()) {
                                            cbxJnsPembayaran.setSelectedItem(cmbtm);
                                        }
                                    }
                                } catch (Exception e) {
                                }

                            }

                        });

                        final Combobox cbxJnsStatus = new Combobox();

                        cbxJnsStatus.setModel(new ListModelList(dataPembayaranStatus, true)); // cmbKota = id zk combobox
                        cbxJnsStatus.setItemRenderer(new ComboitemRenderer() {
                            @Override
                            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                                try {
                                    TrPaymentStatus tsk = (TrPaymentStatus) t;
                                    cmbtm.setLabel(tsk.getPaymentStatus());
                                    cmbtm.setValue(tsk);
                                    // set selected item
                                    if (data.getIdPayment().getIdPaymentStatus() != null) {
                                        if (data.getIdPayment().getIdPaymentStatus().getIdPaymentStatus() == tsk.getIdPaymentStatus()) {
                                            cbxJnsStatus.setSelectedItem(cmbtm);
                                        }
                                    }
                                } catch (Exception e) {
                                }

                            }

                        });

                        final Calendar cal1 = new GregorianCalendar();
                        final Calendar cal2 = new GregorianCalendar();

                        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                        final TtRoomRate rate = loadBean.findMaxRoomRate(data.getTtLogRoomList().get(0).getIdRoomRate().getIdRoom());
                        cal1.setTime(data.getCheckin());
                        cal2.setTime(data.getCheckout());

                        Button btnSave = new Button();
                        btnSave.setIconSclass("z-icon-check");
                        btnSave.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
                            @Override
                            public void onEvent(Event t) {
                                Messagebox.show("Save Payment?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
                                    public void onEvent(Event evt) {
                                        if (evt.getName().equals("onOK")) {
                                            try {

                                                TtPayment payment = new TtPayment();
                                                // booked
                                                payment.setIdJnsPayment((TrJnsPayment) cbxJnsPembayaran.getSelectedItem().getValue());
                                                payment.setIdPaymentStatus((TrPaymentStatus) cbxJnsStatus.getSelectedItem().getValue());
                                                payment.setWaktuPayment(new Date());
                                                payment.setTotal(rate.getRate() * daysBetween(cal1.getTime(), cal2.getTime()));
                                                loadBean.simpanObject(payment);

                                                data.setIdPayment(payment);
                                                loadBean.ubahObject(data);

                                                loadData(null, null);
                                                alert("Data Tersimpan !");
                                                btnIn.setDisabled(false);
                                            } catch (Exception e) {
                                            }

                                        }
                                    }
                                });
                            }

                        });

                        Label lblTotPayment = new Label();
                        lblTotPayment.setValue(" Total : " + daysBetween(cal1.getTime(), cal2.getTime()) + " days : " + rate.getRate() * daysBetween(cal1.getTime(), cal2.getTime()));

                        cellStatus.appendChild(cbxJnsPembayaran);
                        cellStatus.appendChild(cbxJnsStatus);
                        cellStatus.appendChild(btnSave);
                        cellStatus.appendChild(lblTotPayment);
                        cellStatus.appendChild(btnIn);

                        lstm.appendChild(cellStatus);

                        lstm.setValue(data);

                    }

                });

            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("err");
        }

    }

    public int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

}
