/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearningtubes;

/**
 *
 * @author Hafidz Lazuardi
 */
public class KMT {
   private String namakel;
   private String namapel;
   private String namaten;
   
   public KMT ( String namakel, String namapel,String namaten){
       setNamakel(namakel);
       setNamapel(namapel);
       setNamaten(namaten);
   }

    public String getNamakel() {
        return namakel;
    }

    public void setNamakel(String namakel) {
        this.namakel = namakel;
    }

    public String getNamapel() {
        return namapel;
    }

    public void setNamapel(String namapel) {
        this.namapel = namapel;
    }

    public String getNamaten() {
        return namaten;
    }

    public void setNamaten(String namaten) {
        this.namaten = namaten;
    }
   
   
   
}
