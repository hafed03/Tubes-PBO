/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import View.Hal_Admin;
import elearningtubes.KMT;
import elearningtubes.Kelas;
import elearningtubes.MataPelajaran;
import elearningtubes.Materi;
import elearningtubes.Relasi;
import elearningtubes.Student;
import elearningtubes.Tentor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hafidz Lazuardi
 */
public class Database {
    private Connection con;
    private Hal_Admin view;
    private Statement stmt = null;
    

    public void connect(){
        try{
            String url="jdbc:mysql://localhost:3306/database_elearning";
            String username="root";
            String password="";
            con=DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database.");
        } catch(SQLException se){
            System.out.println("Connection error.");
        }
   }
    
    public void query(String SQLString) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQLString);
        } catch (SQLException c) {
            throw new SQLException("Error eksekusi query");
        }
    }
    
    public ArrayList<Tentor> loadAllTentor(){
        try{
            ArrayList<Tentor> tentors = new ArrayList();
            String query="select * from tentor";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Tentor t;
            while (rs.next()){
                String id=rs.getString(1);
                String name=rs.getString(2);
                int umur = rs.getInt(3);
                t = new Tentor(name,id,umur); 
                tentors.add(t);
            }
            return tentors;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Student> loadAllStudent(){
        try{
            ArrayList<Student> students = new ArrayList();
            String query="select * from student";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Student st;
            while (rs.next()){
                String id=rs.getString(1);
                String name=rs.getString(2);
                int semester = Integer.parseInt(rs.getString(3));
                String jurusan = rs.getString(4);
                st = new Student(id,name,semester,jurusan); 
                students.add(st);
            }
            return students;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<KMT> loadallKMT(){
        try{
            ArrayList<KMT> daftarKMT = new ArrayList();
            String query= "select * from viewkelpelten;";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            KMT t;
            while (rs.next()){
                String namakelas=rs.getString(1);
                String namamapel=rs.getString(2);
                String namaten =rs.getString(3);
                t = new KMT(namakelas,namamapel,namaten);
                daftarKMT.add(t);
            }
            return daftarKMT;
        }catch(SQLException se){
            return null;
        }
    }
      
    public ArrayList<Materi> getMk(){
        ArrayList<Materi> dd = new ArrayList();
        String query = "select * from materi";
        try {
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            while (rs.next()){
                Materi mk = new Materi(rs.getString("namamat"),rs.getString("kodemat"));
                dd.add(mk);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return dd;
    }
    
    public ArrayList<Materi> loadAllMateri(){
        try{
            ArrayList<Materi> daftarMateri = new ArrayList();
            String query="select * from materi";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Materi t;
            while (rs.next()){
                String name=rs.getString(1);
                String jk=rs.getString(2);
                t = new Materi(name, jk);
                daftarMateri.add(t);
            }
            return daftarMateri;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Relasi> loadStudentKelas() {
        try {
            ArrayList Relasi = new ArrayList();
            String query="select * from kelasstudent";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Relasi r;
            while (rs.next()){
                String nama = rs.getString(1);
                String kelas = rs.getString(2);
                r = new Relasi(nama,kelas);
                Relasi.add(r);
            }
            return Relasi;
        } catch (SQLException se) {
            return null;
        }
    }
    
    public ArrayList<Relasi> loadKelasMateri() {
        try {
            ArrayList Relasi = new ArrayList();
            String query="select * from kelasmateri";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Relasi r;
            while (rs.next()){
                String kelas = rs.getString(1);
                String materi = rs.getString(2);
                r = new Relasi(kelas,materi);
                Relasi.add(r);
            }
            return Relasi;
        } catch (SQLException se) {
            return null;
        }
    }
    
    public ArrayList<Relasi> loadKelasTentor() {
        try {
            ArrayList Relasi = new ArrayList();
            String query="select * from kelastentor";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Relasi r;
            while (rs.next()){
                String namatentor = rs.getString(1);
                String kelas = rs.getString(2);
                r = new Relasi(namatentor,kelas);
                Relasi.add(r);
            }
            return Relasi;
        } catch (SQLException se) {
            return null;
        }
    }

    public ArrayList<Relasi> loadKelasMapel() {
        try {
            ArrayList Rel = new ArrayList();
            String query="select * from kelasmapel";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Relasi r;
            while (rs.next()){
                String kelas = rs.getString(1);
                String mapel = rs.getString(2);
                r = new Relasi(kelas,mapel);
                Rel.add(r);
            }
            return Rel;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Kelas> loadAllKelas(){
        try{
            ArrayList<Kelas> Classes = new ArrayList();
            String query="select * from kelas";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Kelas k;
            while (rs.next()){
                String name=rs.getString(1);
                k = new Kelas(name);
                Classes.add(k);
            }
            return Classes;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<MataPelajaran> loadAllMapel(){
        try{
            ArrayList<MataPelajaran> Lessons = new ArrayList();
            String query="select * from mapel";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            MataPelajaran mp;
            while (rs.next()){
                String id=rs.getString(1);
                String name = rs.getString(2);
                mp = new MataPelajaran(id,name);
                Lessons.add(mp);
            }
            return Lessons;
        }catch(SQLException se){
            return null;
        }
    }
    
    public ArrayList<Relasi> loadAllKT(String namet){
        try{
            ArrayList<Relasi> daftarKT = new ArrayList();
            String query="select * from kelastentor where namatentor='"+namet+"'";
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery(query);
            Relasi t;
            while (rs.next()){
                String nama=rs.getString(2);
                String kelas=rs.getString(3);
                t = new Relasi(nama,kelas);
                daftarKT.add(t);
            }
            return daftarKT;
        }catch(SQLException se){
            return null;
        }
    }
    
    
//    public ArrayList<Materi> getMk(){
//        ArrayList<Materi> dd = new ArrayList();
//        String s = "select * from materi";
//        try {
//            ResultSet rs = getData(s);
//            while (rs.next()){
//                Materi mk = new Materi(rs.getString("namamat"),rs.getString("kodemat"));
//                dd.add(mk);
//            }
//        } catch (Exception e){
//            e.getStackTrace();
//        }
//        return dd;
//    }


//    public ResultSet getData(String SQLString) {
//        ResultSet rs = null;
//        try {
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(SQLString);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return rs;
//    }

    public Tentor loadOneTentorById(String id){
        try{
        String query="select * from tentor where idTentor ='"+id+"'" ;
        Statement s=con.createStatement();
        ResultSet rs=s.executeQuery(query);
        Tentor t = null;
        while (rs.next()){
                String name = rs.getString(2);
                int age = Integer.parseInt(rs.getString(3));
                t = new Tentor(name,age); 
        }
        return t;
        }catch(SQLException se){
            return null;
        }
    }

    public void editTnt(String namalm, String namabr, int umur,String id){
        String s = "update tentor set namaTentor = '"+namabr+"', umurTentor = '"+umur
                +"' where idTentor = '"+id+"'";
        String q ="update kelastentor set namatentor = '"+namabr+"' where namatentor='"+namalm+"'";
        try {
            query(s);
            query(q);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public Student loadOneStudentById(String id){
        try{
        String query="select * from student where idStudent ='"+id+"';" ;
        Statement s=con.createStatement();
        ResultSet rs=s.executeQuery(query);
        Student st = null;
        while (rs.next()){
            String name=rs.getString(2);
            int semester = Integer.parseInt(rs.getString(3));
            String jurusan = rs.getString(4);
            st = new Student(name,semester,jurusan); 
        }
        return st;
        }catch(SQLException se){
            return null;
        }
    }
    
    public void editStudent(String name, int smt, String jurusan, String id){
        String s = "update student set nameStudent = '"+name+"', smt = '"+smt
                +"', jurusan = '"+jurusan+"' where idStudent = '"+id+"';";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    //database kelas
        
    public void saveMk(String kodmat, String namamat){
        String s = "insert into materi values('"+namamat+"','"+kodmat+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void editMk(String kodmat, String kode, String nama){
        String s = "update materi set kodemateri = '"+kode+"', namamateri = '"+nama+"' where kodemateri = '"+kodmat+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public Materi loadOneMKByKode(String kode){
        try{
        String query="select * from materi where kodemateri ='"+kode+"'" ;
        Statement s=con.createStatement();
        ResultSet rs=s.executeQuery(query);
        Materi kd = null;
        while (rs.next()){
            String kodmat =rs.getString(1);
            String namamat =rs.getString(2);
            kd = new Materi(kodmat,namamat); 
        }
        return kd;
        }catch(SQLException se){
            return null;
        }
    }
    
    public void deleteMk(String kode){
        String s = "delete from materi where kodemateri = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void saveStudenttoClass(String student, String namakelas) {
        String s = "delete from kelasstudent where namaKelas = '"+namakelas+"' and namastudent = '"+student+"';";
        String p = "insert into kelasstudent values('"+student+"','"+namakelas+"');";
        try {
            query(s);
            query(p);
        }catch (SQLException e){
            e.getStackTrace();
        }
    }

//    public Kelas loadOneClassByName(String nama){
//        try{
//        String query="select * from kelas where namakelas ='"+nama+"'" ;
//        Statement s=con.createStatement();
//        ResultSet rs=s.executeQuery(query);
//        Kelas k = null;
//        while (rs.next()){
//            String name = rs.getString(1);
//            k = new Kelas(name); 
//        }
//        return k;
//        }catch(SQLException se){
//            return null;
//        }
//    }
//    
    public void saveKelas(Kelas k) throws SQLException {
        String s = "insert into kelas values ('"+k.getNama()+"');";
        query(s);
    }
    
    public void updateKelas(String lama, String baru) throws SQLException   {
        String s = "update kelas set namakelas = '"+baru+"' where namakelas like '"+lama+"';";
        query(s);
        if (lama != baru) {
            s = "update kelasmapel set namakelas = '"+baru+"' where namakelas like '"+lama+"';";
            query(s);
        }
    }
    
    public void deleteKelas(String nama) throws SQLException {
        String s = "delete from kelas where namakelas = '"+nama+"';";
        query(s);
        s = "delete from kelasmapel where namakelas = '"+nama+"';";
        query(s);
    }
    
    public void saveMapel(MataPelajaran mp) throws SQLException {
        String s = "insert into mapel values ('"+mp.getKodemapel()+"','"+mp.getNamapel()+"');";
        query(s);
    }

    public void updateMapel(String lama, String baru) throws SQLException {
        String s = "update mapel set namaMapel = '"+baru+"' where namaMapel like '"+lama+"';";
        query(s);
        if (lama != baru) {
            s = "update kelasmapel set namaMapel = '"+baru+"' where namaMapel like '"+lama+"';";
            query(s);
        }
    }

    public void deleteMapel(String x) throws SQLException {
        String s = "delete from mapel where namaMapel = '"+x+"';";
        query(s);
        s = "delete from kelasmapel where namaMapel = '"+x+"';";
        query(s);
    }
    
    public void saveTentor(Tentor t) throws SQLException {
        String s = "insert into tentor values ('"+t.getId()+"','"+t.getName()+"',"+t.getAge()+");";
        query(s);
    }

    public void UpdateMapelForKelas(String mapel, String kelas) throws SQLException {
        try {    
            String s = "insert into kelasmapel values ('"+kelas+"','"+mapel+"');";
            query(s);
        } catch (SQLException se) {
            String s = "update kelasmapel set namaMapel ='"+mapel+"' where namakelas = '"+kelas+"';";
            query(s);
        }
    }
    
    public void addKelastoTentor(String kelas, String tentor) throws SQLException {
        String s = "delete from kelastentor where namakelas = '"+kelas+"' and namatentor = '"+tentor+"';";
        query(s);
        s = "insert into kelastentor values ('"+tentor+"','"+kelas+"');";
        query(s);
    }
    
    public void addMateriForKelas(String kelas, String materi) throws SQLException {
        String s = "delete from kelasmateri where namakelas = '"+kelas+"' and namamateri = '"+materi+"';";
        query(s);
        s = "insert into kelasmateri values ('"+kelas+"','"+materi+"');";
        query(s);
    }

    public void saveStudent(String nim, String nama, int smt, String jrs) throws SQLException {
        String s = "insert into student values ('"+nim+"','"+nama+"','"+smt+"','"+jrs+"');";
        query(s);
    }
}