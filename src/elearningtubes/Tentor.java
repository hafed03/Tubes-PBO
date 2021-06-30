/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearningtubes;

import java.util.*;
public class Tentor extends Person {
    private List<Kelas> listKelas;
    private int umur;
    private int jumKelas = 0;
    
    public Tentor(String nama, String id, int umur) {
        super(nama,id);
        setAge(umur);
        listKelas = new ArrayList();
    }
    
    public Tentor(String nama, int umur) {
        super(nama);
        setAge(umur);
        listKelas = new ArrayList();
    }

    public List getKelas() {
        return listKelas;
    }
    
    public String toName(){
         return getName();
    }
    
    public int toAge(){
        return getAge();
    }
    
    public void createKelas (String namaKelas) {
        Kelas k = new Kelas(namaKelas);
        addKelas(k);
    }
    
    public void addKelas(Kelas k) {
        listKelas.add(k);
        jumKelas++;
    }

    public int getAge() {
        return umur;
    }
    
    public void removeKelas(Kelas k){
        int i = 0;
        while((i<listKelas.size()) && (k != listKelas.get(i))){
            i++;
        }
        listKelas.remove(listKelas.get(i));
    }

    public void setAge(int umur) {
        if (umur > 20) {
            this.umur = umur;
        }
    }
    
//    public void display() {
//        System.out.println("Nama tentor: "+getName());
//        System.out.println("ID tentor  : "+getId());
//        System.out.println("Umur tentor: "+umur);
//        System.out.println("Jumlah kelas yang diajar: "+jumKelas);
//        System.out.println("");
//        for(int i = 0; listKelas.size() > i; i++) {
//            System.out.println(i+1);
//            listKelas.get(i).display();
//            System.out.println("========================\n");
//        }
//    }
}

