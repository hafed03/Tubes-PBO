/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author AGUTUGA
 */
public class ControllerLogin extends MouseAdapter implements ActionListener {
    Login view;
    
    public ControllerLogin() {
        view = new Login();
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnAdmin())) {
            view.dispose();
            new ControllerLoginAdmin();
        } else if (source.equals(view.getBtnMhs())) {
            view.dispose();
            new ControllerStudent();
        } else if (source.equals(view.getBtnTentor())) {
            view.dispose();
            new ControllerTentor();
        } else if (source.equals(view.getDaftarStd())) {
            view.dispose();
            new ControllerDaftar();
        }
    }
}
