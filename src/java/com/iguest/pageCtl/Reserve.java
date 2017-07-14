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
import com.iguest.entity.TrJnsLogRoom;
import com.iguest.entity.TrJnsUser;
import com.iguest.entity.TtLogRoom;
import com.iguest.entity.TtRoomRate;
import com.iguest.entity.TtRoomRent;
import com.iguest.service.LoadDataBean;
import com.iguest.utils.CalendarEventUtil;
import com.iguest.utils.Enkripsi;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author dewa
 */
@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class Reserve extends SelectorComposer<Component> {

    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;

    @Wire
    Button btnBaru;

    @Wire
    Listbox listBdGuest, listBdRoom, lbData;

    @Wire
    Label txtRate, lblKet;

    @Wire
    Datebox dbxCheckin, dbxCheckout;

    @Wire
    Bandbox bd, bdRoom;

    @Wire
    Calendars calendars;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        loadListGuest(null, null);
        loadListRoom(null, null);
        loadList();
        loadCalendar();
    }

    @Listen("onClick=#btnBaru")
    public void tambahGuest() {
        Window window = (Window) Executions.createComponents("/widgets/menu/navbar/pages/data/guest.zul", null, null);
        window.setTitle("Guest List");
        window.setWidth("70%");
        window.setHeight("50%");
        window.setClosable(true);
        window.setPosition("center");
        window.doModal();

    }

    @Listen("onClick=#btnHapus")
    public void hapus() {
        if (lbData.getSelectedItem() != null) {
            TtRoomRent data = lbData.getSelectedItem().getValue();
            if (data != null) {
                // hapus
                loadBean.hapusObject(data);
                clear();
                loadList();
            }
        } else {
            // belum dipilih
        }
        loadCalendar();
    }

    @Listen("onClick=#btnSimpan")
    public void simpan() {
        try {
            // insert tt room rent

            TtRoomRent data = new TtRoomRent();
            data.setCheckin(dbxCheckin.getValue());
            data.setCheckout(dbxCheckout.getValue());
            data.setWaktuRekam(new Date());
            data.setIdGuest((TdGuest) listBdGuest.getSelectedItem().getValue());

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        clear();
        loadList();
        loadCalendar();
    }

    @Listen("onClick=#btnTambah")
    public void tambah() {
        clear();
    }

    @Listen("onClick=#prev")
    public void prev() {
        calendars.previousPage();
    }

    @Listen("onClick=#next")
    public void next() {
        calendars.nextPage();
    }

    public void clear() {
        txtRate.setValue("");
        lblKet.setValue("");
        dbxCheckin.setValue(null);
        dbxCheckout.setValue(null);

        bd.setValue(null);
        listBdGuest.setSelectedItem(null);

        bdRoom.setValue(null);
        listBdRoom.setSelectedItem(null);
    }

    @Listen("onChanging=#bd")
    public void clickList(Event event) {
        final Bandbox band = (Bandbox) event.getTarget();
        final org.zkoss.zk.ui.event.InputEvent inputEvent = (org.zkoss.zk.ui.event.InputEvent) event;
        final String initialText = inputEvent.getValue();
        if (initialText.equals("")) {
            loadListGuest(null, null);
        } else {
            loadListGuest(initialText, null);
        }

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

    public void loadListGuest(String nama, final TdGuest dataLama) {
        if (nama != null) {

            List<TdGuest> data = loadBean.listGuestName(nama);
            listBdGuest.setModel(new ListModelList(data, true));

            listBdGuest.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem lstm, Object t, int i) throws Exception {
                    TdGuest data = (TdGuest) t;

                    if (dataLama != null) {
                        if (data.getIdGuest() == dataLama.getIdGuest()) {
                            listBdGuest.setSelectedItem(lstm);
                        }
                    }

                    Listcell cellJenis = new Listcell();
                    Label lblJenis = new Label();

                    lblJenis.setValue(" (" + data.getNoIdentitas() + " - " + data.getIdJnsIdentitas().getJnsIdentitas() + ")");
                    cellJenis.appendChild(lblJenis);
                    lstm.appendChild(cellJenis);

                    lstm.setValue(data);
                    lstm.setLabel(data.getNama());

                }

            });

        } else {
            List<TdGuest> data = loadBean.listGuest();
            listBdGuest.setModel(new ListModelList(data, true));

            listBdGuest.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem lstm, Object t, int i) throws Exception {
                    TdGuest data = (TdGuest) t;

                    if (dataLama != null) {
                        if (data.getIdGuest() == dataLama.getIdGuest()) {
                            listBdGuest.setSelectedItem(lstm);
                        }
                    }

                    Listcell cellJenis = new Listcell();
                    Label lblJenis = new Label();
                    lblJenis.setValue(" (" + data.getNoIdentitas() + " - " + data.getIdJnsIdentitas().getJnsIdentitas() + ")");
                    cellJenis.appendChild(lblJenis);
                    lstm.appendChild(cellJenis);

                    lstm.setValue(data);
                    lstm.setLabel(data.getNama());

                }

            });
        }

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

    @Listen("onClick=#lbData")
    public void clickList() {
        if (lbData.getSelectedItem() != null) {
            try {
                TtRoomRent data = lbData.getSelectedItem().getValue();
                dbxCheckin.setValue(data.getCheckin());
                dbxCheckout.setValue(data.getCheckout());

                bd.setValue(data.getIdGuest().getNama());
                loadListGuest(null, data.getIdGuest());
                TtLogRoom ttLogRoom = loadBean.findLogRoom(data, new TrJnsLogRoom(new Integer("11")));
               
                bdRoom.setValue(ttLogRoom.getIdRoomRate().getIdRoom().getNamaRoom());
                loadListRoom(null, ttLogRoom.getIdRoomRate().getIdRoom());
                txtRate.setValue(ttLogRoom.getIdRoomRate().getRate().toString() + " / night");
                loadCalendarSelect(data);
                hitungCost(ttLogRoom.getIdRoomRate().getIdRoom());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void loadList() {
        List<TtRoomRent> data = loadBean.listRoomRent();
        lbData.setModel(new ListModelList(data, true));

        lbData.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem lstm, Object t, int i) throws Exception {
                TtRoomRent data = (TtRoomRent) t;

                Listcell cellNama = new Listcell();
                Label lblNama = new Label();
                lblNama.setValue(data.getIdGuest().getNama());
                cellNama.appendChild(lblNama);
                lstm.appendChild(cellNama);

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

                lstm.setValue(data);

            }

        });
    }

    public void hitungCost(TdRoom room){
            TtRoomRate rate = loadBean.findMaxRoomRate(room);
            Date dateIn = dbxCheckin.getValue();
            Date dateOut = dbxCheckout.getValue();

            Calendar cal1 = new GregorianCalendar();
            Calendar cal2 = new GregorianCalendar();

            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");

            cal1.setTime(dateIn);
            cal2.setTime(dateOut);

            lblKet.setValue("Total cost "+daysBetween(cal1.getTime(), cal2.getTime())+" days : " + rate.getRate() *daysBetween(cal1.getTime(), cal2.getTime()));

    }
    
    @Listen("onChange=#dbxCheckout")
    public void clickdbxCheckout() {
        if (dbxCheckout.getValue() != null) {
            if(listBdRoom.getSelectedItem()!=null){
                hitungCost((TdRoom) listBdRoom.getSelectedItem().getValue());
            }
            
        }

    }
    
    public int daysBetween(Date d1, Date d2){
             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
     }

    public void loadCalendar() {
        List<TtRoomRent> data = loadBean.listRoomRent();
        List<CalendarEvent> calendarEvents = new LinkedList<CalendarEvent>();
        for (TtRoomRent ttRoomRent : data) {

            // date plus one
            Date dt = ttRoomRent.getCheckout();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            dt = c.getTime();

            TtLogRoom ttLogRoom = loadBean.findLogRoom(ttRoomRent, new TrJnsLogRoom(new Integer("11")));
            calendarEvents.add(new CalendarEventUtil(ttRoomRent.getCheckin(), dt, "#0D7813", "#4CB052", ttRoomRent.getIdGuest().getNama() + " room : " + ttLogRoom.getIdRoomRate().getIdRoom().getNamaRoom()));
        }

        calendars.setModel(new SimpleCalendarModel(calendarEvents));
    }

    public void loadCalendarSelect(TtRoomRent ttRoomRent) {
        calendars.setModel(null);
        List<CalendarEvent> calendarEvents = new LinkedList<CalendarEvent>();

        // date plus one
        Date dt = ttRoomRent.getCheckout();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();

        TtLogRoom ttLogRoom = loadBean.findLogRoom(ttRoomRent, new TrJnsLogRoom(new Integer("11")));
        calendarEvents.add(new CalendarEventUtil(ttRoomRent.getCheckin(), dt, "#0D7813", "#4CB052", ttRoomRent.getIdGuest().getNama() + " room : " + ttLogRoom.getIdRoomRate().getIdRoom().getNamaRoom()));

        calendars.setModel(new SimpleCalendarModel(calendarEvents));
    }

}
