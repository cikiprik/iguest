/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iguest.pageCtl;

import com.iguest.entity.TdEmployee;
import com.iguest.entity.TrJnsEmployee;
import com.iguest.service.LoadDataBean;
import java.util.List;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
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
public class Employee extends SelectorComposer<Component> {

    @WireVariable(value = "LoadDataBean")
    LoadDataBean loadBean;

    @Wire
    Combobox cmbJnsEmployee;

    @Wire
    Textbox txtNama, txtAlamat, txtHp, txtEmail;

    @Wire
    Listbox lbData;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        loadCmbJnsEmployee(null);
        loadList();
    }

    public void loadCmbJnsEmployee(final TrJnsEmployee selData) {
        List<TrJnsEmployee> data = loadBean.listJnsEmployee();
        cmbJnsEmployee.setModel(new ListModelList(data, true)); // cmbKota = id zk combobox
        cmbJnsEmployee.setItemRenderer(new ComboitemRenderer() {
            @Override
            public void render(Comboitem cmbtm, Object t, int i) throws Exception {
                TrJnsEmployee tsk = (TrJnsEmployee) t;
                cmbtm.setLabel(tsk.getJnsEmployee());
                cmbtm.setValue(tsk);
                // set selected item
                if (selData != null) {
                    if (tsk.getIdJnsEmployee() == selData.getIdJnsEmployee()) {
                        cmbJnsEmployee.setSelectedItem(cmbtm);
                    }
                }
            }

        });

    }

    @Listen("onClick=#btnSimpan")
    public void simpan() {
        try {
            TdEmployee employeeLama = loadBean.findEmployee(txtNama.getValue());
            if (employeeLama != null) {
                employeeLama.setAlamat(txtAlamat.getValue());
                employeeLama.setEmail(txtEmail.getValue());
                employeeLama.setHp(txtHp.getValue());
                employeeLama.setNama(txtNama.getValue());
                employeeLama.setIdJnsEmployee((TrJnsEmployee) cmbJnsEmployee.getSelectedItem().getValue());

                loadBean.ubahObject(employeeLama);
            } else {
                TdEmployee employee = new TdEmployee();
                employee.setAlamat(txtAlamat.getValue());
                employee.setEmail(txtEmail.getValue());
                employee.setHp(txtHp.getValue());
                employee.setNama(txtNama.getValue());
                employee.setIdJnsEmployee((TrJnsEmployee) cmbJnsEmployee.getSelectedItem().getValue());

                loadBean.simpanObject(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        clear();
        loadList();
    }

    @Listen("onClick=#lbData")
    public void clickList() {
        if (lbData.getSelectedItem() != null) {
            TdEmployee employee = lbData.getSelectedItem().getValue();
            txtAlamat.setValue(employee.getAlamat());
            txtEmail.setValue(employee.getEmail());
            txtNama.setValue(employee.getNama());
            txtHp.setValue(employee.getHp());
            loadCmbJnsEmployee(employee.getIdJnsEmployee());
        }

    }

    @Listen("onClick=#btnTambah")
    public void tambah() {
        clear();

    }

    @Listen("onClick=#btnHapus")
    public void hapus() {
        if (lbData.getSelectedItem() != null) {
            TdEmployee employee = lbData.getSelectedItem().getValue();
            if (employee != null) {
                // hapus
                loadBean.hapusObject(employee);
                loadList();
                clear();
            }
        } else {
            // belum dipilih
        }

    }

    public void clear() {
        cmbJnsEmployee.setSelectedItem(null);
        txtAlamat.setValue("");
        txtNama.setValue("");
        txtEmail.setValue("");
        txtHp.setValue("");

    }

    public void loadList() {
        List<TdEmployee> data = loadBean.listEmployee();
        lbData.setModel(new ListModelList(data, true));

        lbData.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem lstm, Object t, int i) throws Exception {
                TdEmployee data = (TdEmployee) t;

                Listcell cellJenis = new Listcell();
                Label lblJenis = new Label();
                lblJenis.setValue(data.getIdJnsEmployee().getJnsEmployee());
                cellJenis.appendChild(lblJenis);
                lstm.appendChild(cellJenis);

                Listcell cellNama = new Listcell();
                Label lblNama = new Label();
                lblNama.setValue(data.getNama());
                cellNama.appendChild(lblNama);
                lstm.appendChild(cellNama);

                Listcell cellAlamat = new Listcell();
                Label lblAlamat = new Label();
                lblAlamat.setValue(data.getAlamat());
                cellAlamat.appendChild(lblAlamat);
                lstm.appendChild(cellAlamat);

                Listcell cellHp = new Listcell();
                Label lblHp = new Label();
                lblHp.setValue(data.getNama());
                cellHp.appendChild(lblHp);
                lstm.appendChild(cellHp);

                Listcell cellEmail = new Listcell();
                Label lblEmail = new Label();
                lblEmail.setValue(data.getEmail());
                cellEmail.appendChild(lblEmail);
                lstm.appendChild(cellEmail);
                lstm.setValue(data);
            }

        });
    }

}
