/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import elearningtubes.Application;
import View.*;
import elearningtubes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static org.omg.CORBA.AnySeqHelper.id;
/**
 *
 * @author Priyoga
 */
public class ControllerStudent extends MouseAdapter implements ActionListener{
        Hal_Student view;
        Application model;
        
        public ControllerStudent(){
        view = new Hal_Student();
        model = new Application();
        view.addActionListener(this);
        view.addMouseAdapter(this);
        view.setVisible(true);
        view.resetView();
        model.loadKMT();
        model.loadMapel();
        model.loadMateri();
        model.loadStudent();
        model.loadKelas();
        model.loadTentors();
        String[] listKelas = model.getKelas();
        view.setCbKelas(listKelas);
        view.setpilkelas(model.getpilkelas());
        viewKMT();
        
    } 
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(view.getBtnPeriksaStudent())){
            //periksa student 
            try {
                String id = view.getSelectedStudentId();
                model.loadStudent();
                String name = model.searchStudentName(id);
                int idx = model.searchStudent(name);
                Student s = model.getStudentByIdx(idx);
                view.setStudentName(s.getName());
                view.setStudentNameEdit(s.getName());
                view.setStudentSmt(s.getSmt());
                view.setStudentSmtEdit(s.getSmt());
                view.setStudentJrs(s.getJurusan());
                view.setStudentJrsEdit(s.getJurusan());
                String[] x = model.getStudentClass(view.getSelectedStudentId());
                view.setpilkelas(x);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, "Gagal periksa", "Error", JOptionPane.ERROR_MESSAGE);
            }
            //end periksa Student
        }else if (source.equals(view.getBtnChange())){
            //update student 
            model.loadStudent();
            String id = view.getSelectedStudentId();
            model.editStudent(view.getNameEdit(),view.getSmtEdit(),view.getJrsEdit(),id);
            JOptionPane.showMessageDialog(view, "Data Berhasil Diubah", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
            //end student
        }else if (source.equals(view.getBtnSave())){
            //regis kelas
            String nama = view.getNameText();
            if (nama == "") {
                JOptionPane.showMessageDialog(view, "Gagal menyimpan. Harap masukkan NIM dan klik periksa.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    model.loadStudent();
                    String student = view.getNameText();
                    String namakelas = view.getCbpilkelas();
                    model.addStudenttoKelas(student,namakelas);
                    model.loadKelas();
                    JOptionPane.showMessageDialog(view, "Data Berhasil Disimpan", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Gagal menyimpan", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            //end regis kelas
        } else if (source.equals(view.getCekMapelBtn())) {
            String kelas = view.getCbpilkelas();
            String mapel = model.getMapelFromKelas(kelas);
            view.setNamaMapel(mapel);
        } else if (source.equals(view.getLogoutBtn())) {
            view.dispose();
            new ControllerLogin();
        }
    }
    
    public void viewKMT(){
        try{
            String [][] ar2 = model.viewKeMapelTe();
            DefaultTableModel mdlTable = (DefaultTableModel) view.getTableView().getModel();
            mdlTable.setRowCount(0);
            if (ar2 != null){
                for ( int i = 0; i < ar2.length;i++){
                    mdlTable.addRow(new Object[]{});
                    view.getTableView().setValueAt(ar2[i][0],i,0); 
                    view.getTableView().setValueAt(ar2[i][1],i,1);
                    view.getTableView().setValueAt(ar2[i][2],i,2);
                }
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("gagal view KMT");
        }
    }
    
        @Override
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if (source.equals(view.getListKelas())){
            String namakelas = view.getSelectednamakelas();
            view.setMateriString(model.getInfoMateriKelas(namakelas));
        }
    }
    
}   
