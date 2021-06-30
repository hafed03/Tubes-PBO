package elearningtubes;

public class Student extends Person {
    private String jurusan;
    private int smt;
    
    public Student(String id, String name, int smt, String jurusan) {
        super(name, id);
        setJurusan(jurusan);
        setSmt(smt);
    }

    public Student(String name, int semester, String jurusan) {
        super(name);
        setJurusan(jurusan);
        setSmt(smt);
    }

    public String toName(){
         return getName();
    }
    
    public int toSmt(){
        return getSmt();
    }
    
    public String toJrs(){
        return getJurusan();
    }
    
    
    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        if (!" ".equals(jurusan)) {
            this.jurusan = jurusan;
        }
    }
    
    public int getSmt() {
        return smt;
    }
    
    public void setSmt(int smt) {
        if (smt > 0) {
            this.smt = smt;
        }
    }
}