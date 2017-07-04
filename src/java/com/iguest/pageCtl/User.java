/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.pageCtl;

import com.iguest.entity.TdEmployee;
import com.iguest.entity.TdUser;
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
public class User extends SelectorComposer<Component> {

    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;

    @Wire
    Combobox cmbJnsUser, cmbStatus;

    @Wire
    Listbox listBdEmployee, lbUser;

    @Wire
    Checkbox lihatPass;

    @Wire
    Textbox txtUsername, txtPassword;

    @Wire
    Bandbox bd;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        loadCmbJnsUser(null);
        loadListEmployee(null, null);
        loadList();
    }

    @Listen("onChanging=#bd")
    public void clickList(Event event) {
        final Bandbox band = (Bandbox) event.getTarget();
        final org.zkoss.zk.ui.event.InputEvent inputEvent = (org.zkoss.zk.ui.event.InputEvent) event;
        final String initialText = inputEvent.getValue();
        if (initialText.equals("")) {
            loadListEmployee(null, null);
        } else {
            loadListEmployee(initialText, null);
        }

    }
    @Listen("onClick=#btnTambah")
    public void tambah() {
        clear();
    }
    
    @Listen("onClick=#btnHapus")
    public void hapus() {
        if (lbUser.getSelectedItem() != null) {
            TdUser user = lbUser.getSelectedItem().getValue();
            if (user != null) {
                // hapus
                loadBean.hapusObject(user);
                clear();
                loadList();
            }
        } else {
            // belum dipilih
        }
    }

    @Listen("onClick=#btnSimpan")
    public void simpan() {
        try {
            TdUser userLama = loadBean.findUser(txtUsername.getValue());
            
             Enkripsi e = new Enkripsi();
            String hash = e.sha256(txtPassword.getValue());
            if (userLama != null) {
                userLama.setIdEmployee((TdEmployee) listBdEmployee.getSelectedItem().getValue());
                userLama.setIdJnsUser((TrJnsUser) cmbJnsUser.getSelectedItem().getValue());
                userLama.setUsername(txtUsername.getValue());
                userLama.setPassword(hash);
                if(String.valueOf(cmbStatus.getSelectedIndex()).equals("0")){
                    userLama.setFlagAktif(true);
                } else {
                     userLama.setFlagAktif(false);
                }
                

                loadBean.ubahObject(userLama);
            } else {
                TdUser user = new TdUser();
                user.setIdEmployee((TdEmployee) listBdEmployee.getSelectedItem().getValue());
                user.setIdJnsUser((TrJnsUser) cmbJnsUser.getSelectedItem().getValue());
                user.setUsername(txtUsername.getValue());
                user.setPassword(hash);
                 if(String.valueOf(cmbStatus.getSelectedIndex()).equals("0")){
                    user.setFlagAktif(true);
                } else {
                     user.setFlagAktif(false);
                }

                loadBean.simpanObject(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        clear();
        loadList();
    }

    public void clear() {
        txtUsername.setValue("");
        txtPassword.setValue("");
        cmbJnsUser.setSelectedItem(null);
        bd.setValue(null);
        listBdEmployee.setSelectedItem(null);
        cmbStatus.setSelectedItem(null);
    }

    public void loadListEmployee(String nama, final TdEmployee tdemployee) {
        if (nama != null) {

            List<TdEmployee> data = loadBean.listEmployeeName(nama);
            listBdEmployee.setModel(new ListModelList(data, true));

            listBdEmployee.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem lstm, Object t, int i) throws Exception {
                    TdEmployee data = (TdEmployee) t;

                    if (tdemployee != null) {
                        if (data.getIdEmployee() == tdemployee.getIdEmployee()) {
                            listBdEmployee.setSelectedItem(lstm);
                        }
                    }

                    Listcell cellJenis = new Listcell();
                    Label lblJenis = new Label();
                    lblJenis.setValue(" (" + data.getIdJnsEmployee().getJnsEmployee() + ")");
                    cellJenis.appendChild(lblJenis);
                    lstm.appendChild(cellJenis);

                    lstm.setValue(data);
                    lstm.setLabel(data.getNama());

                }

            });

        } else {
            List<TdEmployee> data = loadBean.listEmployee();
            listBdEmployee.setModel(new ListModelList(data, true));

            listBdEmployee.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem lstm, Object t, int i) throws Exception {
                    TdEmployee data = (TdEmployee) t;

                    if (tdemployee != null) {
                        if (data.getIdEmployee() == tdemployee.getIdEmployee()) {
                            listBdEmployee.setSelectedItem(lstm);
                        }
                    }
                    
                    Listcell cellJenis = new Listcell();
                    Label lblJenis = new Label();
                    lblJenis.setValue(" (" + data.getIdJnsEmployee().getJnsEmployee() + ")");
                    cellJenis.appendChild(lblJenis);
                    lstm.appendChild(cellJenis);

                    lstm.setValue(data);
                    lstm.setLabel(data.getNama());

                }

            });
        }

    }

    public void loadCmbJnsUser(final TrJnsUser selData) {
        List<TrJnsUser> data = loadBean.listJnsUser();
        cmbJnsUser.setModel(new ListModelList(data, true)); // cmbKota = id zk combobox
        cmbJnsUser.setItemRenderer(new ComboitemRenderer() {
            @Override
            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                TrJnsUser tsk = (TrJnsUser) t;
                cmbtm.setLabel(tsk.getJnsUser());
                cmbtm.setValue(tsk);
                // set selected item
                if (selData != null) {
                    if (tsk.getIdJnsUser() == selData.getIdJnsUser()) {
                        cmbJnsUser.setSelectedItem(cmbtm);
                    }
                }
            }

        });

    }

    @Listen("onClick=#lihatPass")
    public void lihatPass() {
        if (lihatPass.isChecked()) {
            txtPassword.setType("text");
        } else {
            txtPassword.setType("password");
        }

    }

    @Listen("onClick=#lbUser")
    public void clickList() {
        if (lbUser.getSelectedItem() != null) {
            TdUser user = lbUser.getSelectedItem().getValue();
            txtUsername.setValue(user.getUsername());
            txtPassword.setValue(user.getPassword());
            loadCmbJnsUser(user.getIdJnsUser());

            if (user.getFlagAktif()) {
                cmbStatus.setSelectedIndex(0);
            } else {
                cmbStatus.setSelectedIndex(1);
            }

            bd.setValue(user.getIdEmployee().getNama());
            loadListEmployee(null, user.getIdEmployee());

        }

    }

    public void loadList() {
        List<TdUser> data = loadBean.listUser();
        lbUser.setModel(new ListModelList(data, true));

        lbUser.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem lstm, Object t, int i) throws Exception {
                TdUser data = (TdUser) t;

                Listcell cellEmployee = new Listcell();
                Label lblEmployee = new Label();
                lblEmployee.setValue(data.getIdEmployee().getNama());
                cellEmployee.appendChild(lblEmployee);
                lstm.appendChild(cellEmployee);

                Listcell cellNama = new Listcell();
                Label lblNama = new Label();
                lblNama.setValue(data.getUsername());
                cellNama.appendChild(lblNama);
                lstm.appendChild(cellNama);

                Listcell cellJns = new Listcell();
                Label lblJns = new Label();
                lblJns.setValue(data.getIdJnsUser().getJnsUser());
                cellJns.appendChild(lblJns);
                lstm.appendChild(cellJns);

                Listcell cellStatus = new Listcell();
                Label lblStatus = new Label();
                if (data.getFlagAktif().toString().equals("true")) {
                    lblStatus.setValue("Aktif");
                } else {
                    lblStatus.setValue("Non Aktif");
                }

                cellStatus.appendChild(lblStatus);
                lstm.appendChild(cellStatus);

                lstm.setValue(data);

            }

        });
    }

}
