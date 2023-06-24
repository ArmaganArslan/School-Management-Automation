package models;

import java.sql.Date;

public class OgretimUyesiModel {
    private int ogretimUyesiId;
    private int bolumId;
    private int dersId;
    private String ogrtUyeNo;
    private String adi;
    private String soyadi;
    private Date dogumTarihi;
    private String cinsiyet;
    private String tcNo;

    public OgretimUyesiModel(int ogretimUyesiId, int bolumId, int dersId, String ogrtUyeNo, String adi, String soyadi, Date dogumTarihi, String cinsiyet, String tcNo) {
        this.ogretimUyesiId = ogretimUyesiId;
        this.bolumId = bolumId;
        this.dersId = dersId;
        this.ogrtUyeNo = ogrtUyeNo;
        this.adi = adi;
        this.soyadi = soyadi;
        this.dogumTarihi = dogumTarihi;
        this.cinsiyet = cinsiyet;
        this.tcNo = tcNo;
    }

    public int getOgretimUyesiId() {
        return ogretimUyesiId;
    }

    public void setOgretimUyesiId(int ogretimUyesiId) {
        this.ogretimUyesiId = ogretimUyesiId;
    }

    public int getBolumId() {
        return bolumId;
    }

    public void setBolumId(int bolumId) {
        this.bolumId = bolumId;
    }

    public int getDersId() {
        return dersId;
    }

    public void setDersId(int dersId) {
        this.dersId = dersId;
    }

    public String getOgrtUyeNo() {
        return ogrtUyeNo;
    }

    public void setOgrtUyeNo(String ogrtUyeNo) {
        this.ogrtUyeNo = ogrtUyeNo;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }
}


