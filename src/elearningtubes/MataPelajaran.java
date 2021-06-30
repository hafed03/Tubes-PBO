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
public class MataPelajaran {
    private String namapel;
    private String kodemapel;
    private String[] namaKelas;

    public String getNamapel() {
        return namapel;
    }

    public String getKodemapel() {
        return kodemapel;
    }
    
    public String[] getNamaKelas() {
        return namaKelas;
    }

    public void setNamapel(String namapel) {
        if (!" ".equals(namapel)) {
            this.namapel = namapel;
        }
    }

    public void setKodemapel(String kodemapel) {
        if (!" ".equals(kodemapel)) {
            this.kodemapel = kodemapel;
        }
    }

    public MataPelajaran(String kodemapel, String namapel) {
        setKodemapel(kodemapel);
        setNamapel(namapel);
    }
}