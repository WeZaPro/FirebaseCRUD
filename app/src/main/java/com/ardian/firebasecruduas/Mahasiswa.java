package com.ardian.firebasecruduas;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    private String nim;
    private String nama;
    private String semester;
    private String ipk;
    private String key;
    private String jurusan;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getIpk() {
        return ipk;
    }

    public void setIpk(String ipk) {
        this.ipk = ipk;
    }


    public Mahasiswa(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String toString(){
        return ""+nim+""+
                ""+nama+""+
                ""+semester+""+
                ""+ipk+""+
                ""+ jurusan;
    }
    public Mahasiswa(String NIM, String NAMA, String SEMESTER, String IPK,String JURUSAN){
        nim = NIM;
        nama = NAMA;
        semester = SEMESTER;
        ipk = IPK;
        jurusan = JURUSAN;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
