package Controller;

import elearningtubes.Kelas;
import elearningtubes.Application;
import View.Hal_Admin;
import elearningtubes.MataPelajaran;
import elearningtubes.Tentor;
import java.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ControllerAdmin extends MouseAdapter implements ActionListener {
    Hal_Admin view;
    Application model;
    
    public ControllerAdmin() {
        view = new Hal_Admin();
        model = new Application();
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        RefreshView();
    }
    
    public void RefreshView() {
        view.resetView();
        model.loadMapel();
        model.loadMateri();
        model.loadStudent();
        model.loadKelas();
        model.loadTentors();
        
        String[] listTentor = model.getTentorName();
        String[] kelas = model.loadKelasLama();
        String[] mapel = model.loadMapeltoString();
        String[] listStudent = model.getStudentListName();
        String[] listmateri = model.getMateriList();
        
        view.setListKelas(model.getKelasName());
        view.setNamaKelasLama(kelas);
        view.setKelasDel(kelas);
        view.setPilKelasUpd(kelas);
        view.setPilMapelAdd(mapel);
        view.setPilTentor(listTentor);
        view.setListMapel(model.getMapelName());
        view.setMapelDel(mapel);
        view.setMapelUpd(mapel);
        view.setListTentor(model.getTentorName());
        view.setDaftarKelas(kelas);
        view.setListStudent(listStudent);
        view.setKelasUpd();
        view.setPilMateri(listmateri);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(view.getPilihBtn())) {
            view.setKelasUpd();
        } else if (source.equals(view.getBuatKelasBtn())) {
            String name = view.getAddNamaKelas();
            Kelas k = new Kelas(name);
            
            if (!model.cekDuplikatKelas(k.getNama())) {
                try {
                    model.addKelas(k);
                    view.showMessage("Saving success.", "Result", 1);
                } catch (SQLException se) {
                    view.showMessage("Saving error.", "Result", 1);
                }
                RefreshView();
            } else {
                view.showMessage("Nama kelas sudah ada", "Result", 1);
            }
        } else if (source.equals(view.getUpdateKelasBtn())) {
            String newName = view.getNamaKelasBaru();
            String oldName = view.getNamaKelasLama();
            try {
                model.UpdateKelas(oldName, newName);
                RefreshView();
                view.showMessage("Updating success.", "Result", 1);
            } catch (SQLException se) {
                view.showMessage("Updating error.", "Result", 1);
            }
        } else if (source.equals(view.getUpdMapelKeKelasBtn())) {
            String mapel = view.getPilMapelAdd();
            String kelas = view.getKelasUpdate();
            try{
                model.UpdateMapelForKelas(mapel,kelas);
                RefreshView();
                view.showMessage("Updating success.", "Result", 1);
            } catch (SQLException se) {
                view.showMessage("Updating error.", "Result", 1);
            }
        } else if (source.equals(view.getDeleteKelasBtn())) {
            String Name = view.getDelKelas();
            try {
                model.DeleteKelas(Name);
                RefreshView();
                view.showMessage("Deleting success.", "Result", 1);
            } catch (SQLException se) {
                view.showMessage("Deleting error.", "Result", 1);
            }
        } else if (source.equals(view.getUpdTentorBtn())) { 
            String tentor = view.getPilTentorAdd();
            String kelas = view.getKelasUpdate();
            try {
                if (!model.isKelasExist(kelas, model.getOneTentor(tentor).getKelas())) {
                    model.UpdateKelastoTentor(kelas,tentor);
                    RefreshView();
                    view.showMessage("Updating success.", "Result", 1);
                } else {
                    view.showMessage("Updating error.", "Result", 1);
                }
            } catch (SQLException se) {
                view.showMessage("Updating error.", "Result", 1);
            }
        } else if (source.equals(view.getAddMateriBtn())) {
            String kelas = view.getKelasUpdate();
            String materi = view.getSelectedPilMateri();
            try {
                model.addMaterikeKelas(kelas, materi);
                RefreshView();
                view.showMessage("Adding success.", "Result", 1);
            } catch (SQLException se) {
                view.showMessage("Adding error.", "Result", 1);
            }
        } else if (source.equals(view.getBuatMapelBtn())) {
            if (!"".equals(view.getNamaMpl())) {
                String namaMpl = view.getNamaMpl();
                String kodeMpl = view.getKodeMpl();
                if (!model.cekDuplikatMapel(kodeMpl, namaMpl)) {
                    MataPelajaran mp = new MataPelajaran(kodeMpl,namaMpl);
                    try {
                        model.addMapel(mp);
                        view.showMessage("Saving success.", "Result", 1);
                    } catch (SQLException se) {
                        view.showMessage("Saving error.", "Result", 1);
                    }

                    RefreshView();
                } else {
                    view.showMessage("Kode atau nama Mata Pelajaran sudah ada.", "Result", 1);
                }
            } else {
                view.showMessage("Nama Mapel kosong", "Result", 1);
            }
        } else if (source.equals(view.getUpdateMapelBtn())) {
            String lama = view.getMapelLama();
            String baru = view.getMapelBaru();
            try {
                model.UpdateMapel(lama,baru);
                RefreshView();
                view.showMessage("Updating success.", "Result", 1);
            } catch (SQLException se) {
                view.showMessage("Updating error.", "Result", 1);
            }
        } else if (source.equals(view.getDeleteMapelBtn())) {
            String Name = view.getMapelDel();
            try {
                model.DeleteMapel(Name);
                RefreshView();
                view.showMessage("Deleting success.", "Result", 1);
            } catch (SQLException se) {
                view.showMessage("Deleting error.", "Result", 1);
            }
        } else if (source.equals(view.getDaftarkanTentorBtn())) {
            String name = view.getNamaTentor();
            String id = view.getIdTentor();
            try {
                int age = view.getUmurTentor();
                if ("".equals(name) || "".equals(id)) {
                    view.showMessage("Field nama atau id tidak boleh kosong.", "Error", 2);
                } else if (age <= 20) {
                    view.showMessage("Umur harus lebih besar dari 20.", "Error", 2);
                } else {
                    Tentor t = new Tentor(name, id, age);
                    try {
                        model.addTentor(t);
                        RefreshView();
                        view.showMessage("Saving success.", "Result", 1);
                    } catch (SQLException se) {
                        view.showMessage("Saving error.", "Result", 1);
                    }
                }
            } catch (java.lang.NumberFormatException nfe) {
                view.showMessage("Field umur harus diisi dengan angka lebih dari 20", "Error", 2);
            }
        } else if (source.equals(view.getLogoutBtn())) {
            view.dispose();
            new ControllerLogin();
        }
    }
    
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if (source.equals(view.getKelas())) {
            String name = view.getSelectedKelas();
            String kelas = model.getInfoClass(name);
            String materi = model.getInfoMateriKelas(name);
            view.setInfoClass(kelas);
            view.setInfoMateri(materi);
        } else if (source.equals(view.getMapel())) {
            String name = view.getSelectedMapel();
            String info = model.getInfoMapel(name);
            view.setInfoMapel(info);
        } else if (source.equals(view.getTentor())) {
            String name = view.getSelectedTentor();
            String info = model.getInfoTentor(name);
            view.setInfoTentor(info);
        } else if (source.equals(view.getDaftarKelas())) {
            String kelas = view.getSelectedDaftarKelas();
            String infoStudent = model.getListStudent(kelas);
            String infoTentor = model.getListTentor(kelas);
            view.setDaftarSiswa(infoStudent);
            view.setDaftarTentorAjar(infoTentor);
        } else if (source.equals(view.getListStudent())) {
            String nama = view.getStudent();
            String info = model.getInfoStudent(nama);
            view.setInfoStudent(info);
        }
    }
    
}
