/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Hafidz Lazuardi
 */
import elearningtubes.Application;
import View.Hal_Tentor;
import elearningtubes.Tentor;
import java.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import Database.Database;
import javax.swing.table.DefaultTableModel;

public class ControllerTentor extends MouseAdapter implements ActionListener{
    Hal_Tentor view;
    Application model;
    
    public ControllerTentor(){
        view = new Hal_Tentor();
        model = new Application();
        view.addActionListener(this);
        //view.addMouseAdapter(this);
        view.setVisible(true);
        view.resetView();
        model.loadMateri();
        model.loadMapel();
        model.loadStudent();
        model.loadKelas();
        model.loadTentors();
        ViewMateri();
    } 
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(view.getBtnPeriksa())){
            model.loadTentors();
            String id = view.getSelectedTentorId();
            if(id=="" || model.searchTentorId(id)==-1){
                JOptionPane.showMessageDialog(view, "Data Tidak Boleh Kosong/Data Tidak Ada","Submit Error",JOptionPane.INFORMATION_MESSAGE);
            }else{
                view.setTentorName(model.searchTentorName(id));
                view.setTentorNameEdit(model.searchTentorName(id));
                view.setTentorAge(model.searchTentorAge(id));
                view.setTentorAgeEdit(model.searchTentorAge(id));
                model.loadKT(model.searchTentorName(id));
                ViewKT(id);
            }
        } else if (source.equals(view.getBtnEdit())){
            model.loadTentors();    
            String id = view.getSelectedTentorId();
            model.editDosen(model.searchTentorName(id),view.getNamaEdit(),view.getUmurEdit(),id);            
            ViewKT(id);
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah","Submit Berhasil",JOptionPane.INFORMATION_MESSAGE);
        } else if(source.equals(view.getBtnCreateMateri())){
           String nama=view.getJudulMateriTxt();
           String kode=view.getKodeMateriTxt();
           if((nama.equals("") || kode.equals(""))||(model.searchKodeMK(kode) !=null)){
                JOptionPane.showMessageDialog(view, "Data Tidak Boleh Kosong/Data Sudah Ada","Submit Gagal",JOptionPane.ERROR_MESSAGE);
           }else{
                model.addMatKul(view.getKodeMateriTxt(), view.getJudulMateriTxt());
                JOptionPane.showMessageDialog(view, "Data Berhasil Ditambah","Submit Berhasil",JOptionPane.PLAIN_MESSAGE);
                model.loadMateri();
                ViewMateri();
           }
        } else if(source.equals(view.getBtnHapusMateri())){
            String kode=view.getSelectedKodematHapus();
            if(model.searchKodeMK(kode)==null){
                JOptionPane.showMessageDialog(view, "Data Tidak Boleh Kosong/Tidak Ditemukan","Submit Gagal",JOptionPane.ERROR_MESSAGE);
            }else{
                model.deleteMatkul(view.getHapusMateriTxt());
                model.loadMateri();
                ViewMateri();
                JOptionPane.showMessageDialog(view, "Data Berhasil DiHapus","Submit Berhasil",JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(source.equals(view.getBtnPeriksaMateri())){
            String kode=view.getSelectedKodemat();
            if(model.searchKodeMK(kode)==null){
                JOptionPane.showMessageDialog(view, "Data Boleh Kosong/Tidak Ditemukan","Submit Gagal",JOptionPane.ERROR_MESSAGE);
            }else{
                view.setGantiKodeMateriTxt(model.searchKodeMK(kode));
                view.setGantiNamaMateriTxt(model.searchNamaMK(kode));
            }
        } else if(source.equals(view.getBtnGantiMateri())){
            model.editMatKul(view.getSelectedKodemat(), view.getGantiKodeMateriTxt(), view.getGantiNamaMateriTxt());
            model.loadMateri();
            ViewMateri();
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah","Submit Berhasil",JOptionPane.INFORMATION_MESSAGE);
        } else if (source.equals(view.getLogoutBtn())) {
            view.dispose();
            new ControllerLogin();
        }
    }
    
    public void ViewMateri() {
        try { 
            String[][] ar2 = model.menuListMateri();
            DefaultTableModel mdlTable = (DefaultTableModel) view.getTblStu().getModel();
            mdlTable.setRowCount(0);
            if (ar2 != null) {
                for (int i = 0; i < ar2.length; i++) {
                    mdlTable.addRow(new Object[]{});
                    view.getTblStu().setValueAt(ar2[i][0], i, 0);
                    view.getTblStu().setValueAt(ar2[i][1], i, 1);
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Gagal View Materi");
        }
    }
    
    public void ViewKT(String id) {
        try { 
            String[][] ar2 = model.menuListKT(model.searchTentorName(id));
            view.setTableKT(ar2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
    
