/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.DaftarStudent;
import elearningtubes.Application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AGUTUGA
 */
public class ControllerDaftar implements ActionListener {
    Application model;
    DaftarStudent view;
    
    public ControllerDaftar() {
        view = new DaftarStudent();
        model = new Application();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source.equals(view.getDaftarBtn())) {
            boolean kosong = false;
            if ("".equals(view.getNIM())) {
                kosong = true;
            }
            if ("".equals(view.getNama())) {
                kosong = true;    
            }
            if ("".equals(view.getSmt())) {
                kosong = true;
            }
            if ("".equals(view.getJrs())) {
                kosong = true;
            }
            
            if (kosong) {
                JOptionPane.showMessageDialog(view, "Tidak boleh ada field yang kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int smt = Integer.parseInt(view.getSmt());
                    if (model.cekStudent(view.getNIM())) {
                        model.saveStudent(view.getNIM(),view.getNama(),smt,view.getJrs());
                        JOptionPane.showMessageDialog(view, "Berhasil mendaftar", "Succes", 1);
                        view.dispose();
                        new ControllerLogin();
                    } else {
                        JOptionPane.showMessageDialog(view, "NIM sudah digunakan", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(view, "Gagal menyimpan", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Input tidak valid", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
