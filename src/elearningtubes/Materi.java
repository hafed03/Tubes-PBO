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
public class Materi {
    private String namamat;
    private String kodmat;

    public Materi(String namamat, String kodmat) {
        setNamamat(namamat);
        setKodmat(kodmat);
    }

    public String getNamamat() {
        return namamat;
    }

    public void setNamamat(String namamat) {
        if(!" ".equals(namamat)) {
            this.namamat = namamat;
        }
    }

    public String getKodmat() {
        return kodmat;
    }

    public void setKodmat(String kodmat) {
        if(!" ".equals(kodmat)) {
            this.kodmat = kodmat;
        }
    }
}