/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.LoginAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author AGUTUGA
 */
public class ControllerLoginAdmin extends MouseAdapter implements ActionListener {
    LoginAdmin view;
    int percobaan = 0;
    
    public ControllerLoginAdmin() {
        view = new LoginAdmin();
        view.addActionListener(this);
        view.ResetView();
        view.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnLog())) {
            boolean maypass = false;
            if ("admin".equals(view.getJtUSERNAME())) {
                if ("admin".equals(view.getJtPASS())) {
                    maypass = true;
                }
            }
            if (maypass) {
                view.dispose();
                new ControllerAdmin();
            } else {
                percobaan++;
                view.showMessage("Username atau password salah. Salah 3 kali, program akan keluar. \nTotal kesalahan: " + percobaan, "Login Failed", 2);
                if (percobaan == 3) {
                    view.showMessage("Sudah 3 kali salah, program akan keluar", "Login Failed", 2);
                    view.dispose();
                }
            }
        }
    }
    
    
    
}
