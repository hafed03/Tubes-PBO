/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearningtubes;

/**
 *
 * @author AGUTUGA
 */
public class Relasi {
    private String a;
    private String b;
    
    public Relasi(String a, String b) {
        setA(a);
        setB(b);
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
