/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearningtubes;

import java.util.*;

public class Kelas {
    private List<Student> listStudent = new ArrayList<>();
    private List<Materi> listMateri = new ArrayList<>();
    private MataPelajaran mapel;
    private String namakelas;
    
    public Kelas(String nama) {
        setNamakelas(nama);
    }

    public Kelas(String nama, MataPelajaran mapel, String namakelas) {
        setNamakelas(nama);
        this.mapel = mapel; 
    }
    
    public List<Student> getStudent() {
        return listStudent;
    }
    
    public void addStudent(Student s) {
        listStudent.add(s);
    }
    
    public void addMataPelajaran(MataPelajaran mapel) {
        this.mapel = mapel;
    }
    
    public void createMateri(String namaMat, String kodeMat) {
        Materi M = new Materi(namaMat,kodeMat);
        listMateri.add(M);
    }
    
    public int Hitungsiswa() {
        return listStudent.size();
    }
    
    public int Hitungmateri() {
        return listMateri.size();
    }
    
    public void setNamakelas(String nama) {
        if(!" ".equals(nama)) {
            this.namakelas = nama;
        }
    }

    public String getNama() {
        return namakelas;
    }
    
    public MataPelajaran getMapel() {
        return mapel;
    }
    
    public List<Materi> getMateri() {
        return listMateri;
    }
    
    public String[] loadAllStudent() {
        String[] st = new String[listStudent.size()];
        for (int i = 0; i < listStudent.size(); i++) {
            st[i] = listStudent.get(i).getName();
        }
        return st;
    }
    
    public String display() {
        String x = "Nama Kelas: "+namakelas;
        if (mapel != null) {
            x = x +"\nMata Pelajaran: "+ mapel.getNamapel();
        } else {
            x = x + "\nMata Pelajaran: <Belum ada Mata Pelajaran di kelas ini";
        }
        
//        String mat = "\nDaftar materi di kelas ini:\n";
//        if (listMateri.size() != 0) {
//            for(int i = 0; i < listMateri.size(); i++) {
//                int j = i+1;
//                mat = mat +"-  " + listMateri.get(i).getNamamat()+"\n     Kode materi: " +listMateri.get(i).getKodmat() + "\n";
//            }
//        } else {
//            mat = mat + "<Belum ada Materi di kelas ini>\n";
//        }
        return x;
    }
}
