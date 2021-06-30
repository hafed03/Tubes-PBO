/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elearningtubes;

import Database.Database;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Hafidz Lazuardi
 */
public class Application {
    private List<Student> DaftarStudent; 
    private List<Tentor> DaftarTentor;
    private List<MataPelajaran> DaftarMataPelajaran;
    private List<Kelas> DaftarKelas;
    private List<KMT> DaftarKMT;
    private List<Relasi> RelasiKelasMapel;
    private List<Relasi> RelasiKelasMateri;
    private List<Materi> DaftarMateri;
    private List<Relasi> RelasiTentorKelas;
    private List<Relasi> RelasiStudentKelas;
    private List<Relasi> DaftarKt;
   
    Database db;
   
    public Application() {
        DaftarTentor = new ArrayList();
        DaftarStudent = new ArrayList();
        DaftarKelas = new ArrayList();
        DaftarMataPelajaran = new ArrayList();
        RelasiKelasMapel = new ArrayList();
        RelasiKelasMateri = new ArrayList();
        DaftarMateri = new ArrayList();
        RelasiTentorKelas = new ArrayList();
        RelasiStudentKelas = new ArrayList();
        DaftarKMT = new ArrayList();
        DaftarKt = new ArrayList();
        db = new Database();
        db.connect();
    }
   
   //student
   public void loadKMT(){
       DaftarKMT = db.loadallKMT();
   }
   
   public String [] getKelas() {
        String[] x = new String[DaftarKelas.size()];
        for(int i = 0; i < DaftarKelas.size(); i++) {
            MataPelajaran mapel = DaftarKelas.get(i).getMapel();
            x[i] = DaftarKelas.get(i).getNama();
        }
        return x;
   }
   
   public String [] getpilkelas(){
       String[] x = new String[DaftarKelas.size()];
       for ( int i = 0; i < DaftarKelas.size();i++){
           x[i] = DaftarKelas.get(i).getNama();
       }
       return x;
   } 
   
   public int cariKelasIndex(String namakel) {
       int i =0;
       for(i = 0; i < DaftarKelas.size(); i++) {
           if (DaftarKelas.get(i).getNama() == namakel) {
               break;
           }
       }
       
       if (DaftarKelas.get(i).getNama() == namakel) {
           return i;
       } else {
           return -1;
       }
   }
   
   public Kelas getKelasByIdx(int i) {
       return DaftarKelas.get(i);
   }
   
   //end student
    
   //TENTOR
   
   public Tentor getTentor(String id){
       int i = 0;
       int a = -1;
       boolean find = false;
        for (i=0;i<DaftarTentor.size();i++){
            if (DaftarTentor.get(i).getId().equals(id)) {
                find = true;
                a=i;
            }
        }
        if (find) {
            return DaftarTentor.get(a);
        }else {
            return null;
        }
    }
    
    public String searchTentorName(String id){
        return db.loadOneTentorById(id).toName();
    }
    
    public int searchTentorAge(String id){
        return db.loadOneTentorById(id).toAge();
    }
        
    public String [] getTentorListId(){
        String [] listId = new String [DaftarTentor.size()];
        for (int i = 0; i < DaftarTentor.size();i++){
            listId[i] = DaftarTentor.get(i).getId();
        }
        return listId;
    }
    
    public void editDosen(String namalm,String namabr, int umur, String id){
        db.editTnt(namalm, namabr, umur, id);
        Tentor t = getTentor(id);
        t.setName(namabr);
        t.setAge(umur);
        System.out.println(t.getAge());
        System.out.println(t.getName());
    }
    
    // end tentor
    
    public void addMatKul(String KodeMK, String NamaMK){
        db.saveMk(KodeMK, NamaMK);
        DaftarMataPelajaran.add(new MataPelajaran(KodeMK,NamaMK));
    }
    
    public MataPelajaran getMatkul(String kodemk){
        for (MataPelajaran m : DaftarMataPelajaran){
            if (m.getKodemapel().equals(kodemk))
                return m;
        }
        return null;
    }
    
    public String searchKodeMK(String kode){
        if(db.loadOneMKByKode(kode)==null){
            return null;
        }else{
            return db.loadOneMKByKode(kode).getKodmat();
        }
    }
    
    public String searchNamaMK(String kode){
        return db.loadOneMKByKode(kode).getNamamat();
    }
    
    public void deleteMatkul(String kodemk){
        db.deleteMk(kodemk);
    }
    
    public void editMatKul(String kodeMKlama, String kode, String nama){
        db.editMk(kodeMKlama, kode, nama);
        MataPelajaran m = getMatkul(kode);
    }
    
    public void loadMateri(){
        DaftarMateri = db.loadAllMateri();
    }
    
    public String[][] menuListMateri(){
        if (DaftarMateri.size() != 0) {
            String[][] ar = new String[DaftarMateri.size()][2];
            for (int i = 0; i < DaftarMateri.size(); i++) {
                ar[i][0] = DaftarMateri.get(i).getKodmat();
                ar[i][1] = DaftarMateri.get(i).getNamamat();
            }
            return ar;
        } else {
            return null;
        }
    }
    
    public String[][] menuListKT(String x){
        int idx = searchTentor(x);
        Tentor t = DaftarTentor.get(idx);
        if (idx != -1) {
            String[][] at = new String[RelasiTentorKelas.size()][2];
            List<Kelas> k = t.getKelas();
            int ar2idx =0;
            for (int i = 0; i < k.size(); i++) {
                if (k.get(i).getStudent().size() != 0) {
                    for (int j = 0 ;j < k.get(i).getStudent().size(); j++) {
                        at[ar2idx][0] = k.get(i).getStudent().get(j).getName();
                        at[ar2idx][1] = k.get(i).getNama();
                        System.out.println(at[ar2idx][0]);
                        System.out.println(at[ar2idx][1]);
                        ar2idx++;
                    }
                } else {
                    at[ar2idx][0] = "<Tidak ada Student>";
                    at[ar2idx][1] = k.get(i).getNama();
                    ar2idx++;
                }
            }
            return at;
        } else {
            return null;
        }
    }
    
    public void loadKT(String namet){
        DaftarKt=db.loadAllKT(namet);
    }
    
    // END APLIKASI TENTOR
    
    //APLIKASI STUDENT
    
    public String searchStudentName(String id){
        return db.loadOneStudentById(id).toName();
    }
    
    public int searchStudentSmt(String id){
        return db.loadOneStudentById(id).toSmt();
    }
    
    public String searchStudentJrs(String id){
        return db.loadOneStudentById(id).toJrs();
    }
    
    public void editStudent(String name, int smt, String jurusan, String id){
        db.editStudent(name, smt, jurusan,id);
        Student m = getStudent(id);
          m.setName(name);
          m.setSmt(smt);
          m.setJurusan(jurusan);
    }
    
    public Student getStudent(String id){
        for (int i = 0;i < DaftarStudent.size();i++){
            if (DaftarStudent.get(i).getId().equals(id)){
                return DaftarStudent.get(i);
            }
        }
        return null;
    }
    
    public void addStudenttoKelas(String student,String kelas) throws Exception {
        db.saveStudenttoClass(student, kelas);
    }
    
    public void saveStudent(String nim, String nama, int smt, String jrs) throws SQLException {
        db.saveStudent(nim, nama, smt, jrs);
    }
    
    public boolean cekStudent(String id) {
        return db.loadOneStudentById(id) == null;
    }
    
    public String[][] viewKeMapelTe(){
        if (DaftarKelas.size() != 0){
            String [][] ar = new String [DaftarKelas.size()][3];
            for (int i = 0; i < DaftarKelas.size();i++){
                ar [i][0] = DaftarKelas.get(i).getNama();
                if (DaftarKelas.get(i).getMapel() != null)
                    ar [i][1] = DaftarKelas.get(i).getMapel().getNamapel();
                else 
                    ar [i][1] = "<Belum ada Mata Pelajaran>";
                ar [i][2] = getTentorOfKelas(DaftarKelas.get(i).getNama());
            }
            return ar;
        }else{
            return null;
        }
    }
    
    public String getTentorOfKelas(String kelas) {
        String nama = "";
        boolean ada = false;
        for (int i = 0; i < RelasiTentorKelas.size(); i++) {
            Relasi r = RelasiTentorKelas.get(i);
            if (kelas.equals(r.getB())) {
                nama = nama + r.getA() + ", ";
                ada = true;
            }
        }
        
        if (ada) {
            return nama;
        } else {
            return "<Belum ada tentor yang mengajar>";
        }
    }
   
    public void loadKelas() {
        DaftarKelas = db.loadAllKelas();
        RelasiKelasMapel = db.loadKelasMapel();
        RelasiKelasMateri = db.loadKelasMateri();
        RelasiStudentKelas = db.loadStudentKelas();
        try {
            for(int i = 0; i < RelasiKelasMapel.size(); i++) {
                Relasi r = RelasiKelasMapel.get(i);
                for (int j = 0; j < DaftarKelas.size(); j++) {
                    if(r.getA().equals(DaftarKelas.get(j).getNama())) {         //getA ini untuk mencari nama kelas
                        int idx = searchMapel(r.getB());                        //getB ini untuk mencari nama mapel
                        MataPelajaran mp = DaftarMataPelajaran.get(idx);
                        DaftarKelas.get(j).addMataPelajaran(mp);
                    }
                }
            }
            
            for (int i = 0; i < RelasiKelasMateri.size(); i++) {
                Relasi r = RelasiKelasMateri.get(i);
                int idxM = searchMateri(r.getB());
                int idxK = searchKelas(r.getA());
                String nama = DaftarMateri.get(idxM).getNamamat();
                String kode = DaftarMateri.get(idxM).getKodmat();
                DaftarKelas.get(idxK).createMateri(nama, kode);
            }
            
            for (int i = 0; i < RelasiStudentKelas.size(); i++) {
                Relasi r = RelasiStudentKelas.get(i);
                int idxS = searchStudent(r.getA());
                int idxK = searchKelas(r.getB());
                if (idxS != -1 && idxK != -1) {
                    Student s = DaftarStudent.get(idxS);
                    DaftarKelas.get(idxK).addStudent(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Load Kelas gagal");
            System.out.println(e);
        }
    }
    
    public void addKelas(Kelas k) throws SQLException {
        db.saveKelas(k);
    }
    
    public String[] getKelasName() {
        String[] listKel = new String[DaftarKelas.size()];
        for(int i = 0; i < DaftarKelas.size(); i++) {
            listKel[i] = DaftarKelas.get(i).getNama();
        }
        return listKel;
    }
    
    public void setNameKelas(String lama, String baru) throws SQLException {
        int i = searchKelas(lama);
        if (i != -1) {
            db.updateKelas(lama, baru);
            loadKelas();
        } else {
            System.out.println("Nama kelas tidak ditemukan");
            System.out.println("Updating error.");
        }
    }
    
    public int searchKelas(String x) {
        int i;
        for(i = 0; i < DaftarKelas.size(); i++) {
            if (x.equals(DaftarKelas.get(i).getNama())) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean cekDuplikatKelas(String x) {
        boolean ada = false;
        for (int i = 0; i < DaftarKelas.size(); i++) {
            if (DaftarKelas.get(i).getNama().equals(x)) {
                ada = true;
                break;
            }
        }
        return ada;
    }
    
    public String getInfoClass(String nama) {
        int idx = searchKelas(nama);
        Kelas k = DaftarKelas.get(idx);
        
        int indexTentor = 0;
        String[] namaTentor = new String[DaftarTentor.size()];
        for(int i = 0; i < DaftarTentor.size(); i++) {
            List<Kelas> kelas = DaftarTentor.get(i).getKelas();
            for(int j = 0; j < kelas.size(); j++) {
                if (nama.equals(kelas.get(j).getNama())) {
                    namaTentor[indexTentor] = DaftarTentor.get(i).getName();
                    indexTentor++;
                }
            }
        }
        
        return k.display();
    }
    
    public boolean cekDuplikatMapel(String kode, String nama) {
        boolean ada = false;
        for (int i = 0; i < DaftarMataPelajaran.size(); i++) {
            if ((DaftarMataPelajaran.get(i).getKodemapel().equals(kode)) || (DaftarMataPelajaran.get(i).getNamapel().equals(nama))) {
                ada = true;
                break;
            }
        }
        return ada;
    }
    
    public void addMapel(MataPelajaran mp) throws SQLException {
        db.saveMapel(mp);
    }
    
    public void loadMapel() {
        DaftarMataPelajaran = db.loadAllMapel();
    }
    
    public String[] getMapelName() {
        String[] listMpl = new String[DaftarMataPelajaran.size()];
        for(int i = 0; i < DaftarMataPelajaran.size(); i++) {
            listMpl[i] = DaftarMataPelajaran.get(i).getNamapel();
        }
        return listMpl;
    }
    
    public String getMapelFromKelas(String x) {
        int i = searchKelas(x);
        System.out.println(i);
        Kelas k = DaftarKelas.get(i);
        if (k.getMapel() != null) {
            return k.getMapel().getNamapel();
        } else {
            return "<Belum ada nama mata pelajaran>";
        }
    }
    
    public String getInfoMapel(String nama) {
        String info = "Nama mata pelajaran: "+nama+"\n";
        int idx = searchMapel(nama);
        if (idx != -1) {
            info = info + "Kode mata pelajaran: "+DaftarMataPelajaran.get(idx).getKodemapel()+"\n";
            MataPelajaran mp = DaftarMataPelajaran.get(idx);
            String[] daftarKel = mp.getNamaKelas();
            try {
                info = info+"Daftar kelas yang belajar: \n";
                for(int i = 0; i < daftarKel.length; i++) {
                    int j = i;
                    info = info+j+1+". "+daftarKel[i]+"\n";
                }
            } catch (Exception e) {
                return info;
            }
        }
        return info;
    }
    
    public int searchMapel(String nama) {
        boolean find = false;
        int i;
        for (i = 0; i < DaftarMataPelajaran.size(); i++) {
            if (nama.equals(DaftarMataPelajaran.get(i).getNamapel())) {
                find = true;
                break;
            }
        }
        
        if (find) {
            return i;
        } else {
            return -1;
        }
    }
    
    public String[] loadKelasLama() {
        ArrayList<Kelas> list = db.loadAllKelas();
        String[] Kel = new String[list.size()];
        for(int i = 0; i < Kel.length; i++) {
            Kel[i] = list.get(i).getNama();
        }
        return Kel;
    }
    
    public void UpdateKelas(String lama, String baru) throws SQLException {
        db.updateKelas(lama, baru);
    }
    
    public void DeleteKelas(String x) throws SQLException {
        db.deleteKelas(x);
    }
    
    public String[] loadMapeltoString() {
        ArrayList<MataPelajaran> list = db.loadAllMapel();
        String[] mapel = new String[list.size()];
        for(int i = 0; i < mapel.length; i++) {
            mapel[i] = list.get(i).getNamapel();
        }
        return mapel;
    }

    public void UpdateMapel(String lama, String baru) throws SQLException {
        db.updateMapel(lama, baru);
    }
    
    public void DeleteMapel(String x) throws SQLException {
        db.deleteMapel(x);
    }
    
    public void addTentor(Tentor t) throws SQLException {
        db.saveTentor(t);
    }
    
    public String[] getTentorName() {
        ArrayList<Tentor> list = db.loadAllTentor();
        String[] tentor = new String[list.size()];
        for(int i = 0; i < tentor.length; i++) {
            tentor[i] = list.get(i).getName();
        }
        return tentor;
    }
    
    public String getInfoTentor(String name) {
        int idx = searchTentor(name);
        String info = "Nama Tentor\t: "+name+"\n";
        if (idx != -1) {
            Tentor t = DaftarTentor.get(idx);
            info = info + "Umur Tentor\t: "+t.getAge()+"\n";
            info = info + "ID Tentor\t: "+t.getId()+"\n";
            List<Kelas> listkelas = t.getKelas();
            if (listkelas.size() != 0) {
                info = info + "Kelas yang diajar: \n";
                for (int i = 0; i < listkelas.size(); i++) {
                    int j = i+1;
                    info = info + j +". "+listkelas.get(i).getNama()+"\n";
                }
            } else {
                info = info + "Belum ada kelas yang diajar atau data belum terupdate";
            }
            return info;
        }
        return info;
    }
    
    public void UpdateMapelForKelas(String mapel, String kelas) throws SQLException {
        db.UpdateMapelForKelas(mapel,kelas);
    }
    
    public String[] getMateriList() {
        String[] x = new String[DaftarMateri.size()];
        for (int i = 0; i < x.length; i++) {
            x[i] = DaftarMateri.get(i).getNamamat();
        }
        return x;
    }
    
    public String getInfoMateriKelas(String x) {
        int idx = searchKelas(x);
        if (idx != -1) {
            List<Materi> m = DaftarKelas.get(idx).getMateri();
            if (m.size() != 0) {
                x = "List materi: \n";
                for(int i = 0; i < m.size(); i++) {
                    int j = i+1;
                    x = x + j + ". "+m.get(i).getNamamat()+"\n"
                            + "Kode Materi: "+m.get(i).getKodmat()+"\n";
                }
            } else {
                x = "<Belum ada materi>";
            }
            return x;
        } else {
            return "Error";
        }
    }
    
    public int searchMateri(String x) {
        boolean find = false;
        int i;
        for(i = 0; i < DaftarMateri.size(); i++) {
            if (x.equals(DaftarMateri.get(i).getNamamat())) {
                return i;
            }
        }
        return -1;
    }
    
    public void addMaterikeKelas(String kelas, String materi) throws SQLException {
        db.addMateriForKelas(kelas,materi);
    }
    
    public String getListStudent(String kelas) {
        int idx = searchKelas(kelas);
        int jumSiswa = DaftarKelas.get(idx).Hitungsiswa();
        String[] siswa = DaftarKelas.get(idx).loadAllStudent();
        String info = "Daftar Student di kelas ini: \n";
        if (jumSiswa != 0) {
            for(int i = 0; i < jumSiswa; i++) {
                int j = i+1;
                info = info + j +". "+siswa[i]+"\n";
            }
        } else {
            info = "<Belum ada Student di kelas ini>";
        }
        return info;
    }
    
    public String getListTentor(String kelas) {
        boolean ada = false;
        
        
        String info = "Daftar tentor yang mengajar di kelas ini: \n";
        int no=1;
        for(int i = 0; i < RelasiTentorKelas.size(); i++) {
            if (kelas.equals(RelasiTentorKelas.get(i).getB())) {
                info = info + no +". "+ RelasiTentorKelas.get(i).getA() + "\n";
                ada = true;
                no++;
            }
        }
        if (ada) {
            return info;
        } else {
            return "<Belum ada tentor yang mengajar di kelas ini>";
        }
    }
    
    //APLIKASI TENTOR
   
    public void loadTentors(){
        RelasiTentorKelas = db.loadKelasTentor();
        DaftarTentor = db.loadAllTentor();
        int a=0;
        for(int i = 0; i < RelasiTentorKelas.size(); i++) {
            Relasi r = RelasiTentorKelas.get(i);
            int idxT = searchTentor(r.getA());  //get nama tentor
            if (idxT != -1) {
                int idxK = searchKelas(r.getB());
                if (idxK != -1) {
                    if (!isKelasExist(r.getB(), DaftarTentor.get(idxT).getKelas())) {
                        DaftarTentor.get(idxT).addKelas(DaftarKelas.get(idxK));
                    }
                }
            }
        }
    }
    
    public void UpdateKelastoTentor(String kelas, String tentor) throws SQLException {
        db.addKelastoTentor(kelas,tentor);
    }
    
    public int searchTentor(String name) {
        boolean find = false;
        int i;
        for(i = 0; i < DaftarTentor.size(); i++) {
            if (name.equals(DaftarTentor.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
    
    public int searchTentorId(String id) {
        boolean find = false;
        int i;
        for(i = 0; i < DaftarTentor.size(); i++) {
            if (id.equals(DaftarTentor.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isKelasExist(String x, List<Kelas> k) {
        boolean exist = false;
        for(int i = 0; i < k.size(); i++) {
            if (x.equals(k.get(i).getNama())) {
                exist = true;
            }
        }
        return exist;
    }
    
    public Tentor getOneTentor(String x) {
        int idx = searchTentor(x);
        return DaftarTentor.get(idx);
    }
    
    // END APLIKASI TENTOR
    
    //APLIKASI STUDENT
    
    public int searchStudent(String nama) {
        for(int i = 0; i < DaftarStudent.size();i++) {
            if (nama.equals(DaftarStudent.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
    
    public void loadStudent(){
        DaftarStudent = db.loadAllStudent();
    }
    
    public String [] getStudentListId(){
        String [] listId = new String [DaftarStudent.size()];
        for (int i = 0; i < DaftarStudent.size();i++){
            listId[i] = DaftarStudent.get(i).getId();
        }
        return listId;
    }
    
    public String[] getStudentListName() {
        String [] listName = new String [DaftarStudent.size()];
        for (int i = 0; i < DaftarStudent.size();i++){
            listName[i] = DaftarStudent.get(i).getName();
        }
        return listName;
    }
    
    public String getInfoStudent(String nama) {
        int idx = searchStudent(nama);
        if (idx != -1) {
            Student s = DaftarStudent.get(idx);
            String info = "Nama Student\t: "+s.getName()+"\n"
                    + "ID Student\t: "+s.getId()+"\n"
                    + "Semester\t: "+s.getSmt()+"\n"
                    + "Jurusan\t: "+s.getJurusan()+"\n";
            return info;
        } else {
            return "<Belum ada informasi mengenai mahasiswa ini>";
        }
    }
    
    public Student getStudentByIdx(int i) {
        return DaftarStudent.get(i);
    }
    
    public String[] getStudentClass(String nim) {
        String[] x = new String[DaftarKelas.size()];
        int idx = 0;
        for(int i = 0; i < x.length; i++) {
            Kelas k = DaftarKelas.get(i);
            for(int j = 0; j < k.getStudent().size(); j++) {
                if (nim.equals(k.getStudent().get(j).getId())) {
                    x[idx] = k.getNama();
                    idx++;
                }
            }
        }
        return x;
    }
}