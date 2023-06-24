package models;

import java.sql.Date;

public class OgrenciModel {
    private int ogrenciId;
    private int bolumId;
    private String ogrNo;
    private String adi;
    private String soyadi;
    private String tcNo;
    private String cinsiyet;
    private Date dogumTarihi;

    public OgrenciModel(int ogrenciId, int bolumId, String ogrNo, String adi, String soyadi, String tcNo, String cinsiyet, Date dogumTarihi) {
        this.ogrenciId = ogrenciId;
        this.bolumId = bolumId;
        this.ogrNo = ogrNo;
        this.adi = adi;
        this.soyadi = soyadi;
        this.tcNo = tcNo;
        this.cinsiyet = cinsiyet;
        this.dogumTarihi = dogumTarihi;
    }

    public int getOgrenciId() {
        return ogrenciId;
    }

    public void setOgrenciId(int ogrenciId) {
        this.ogrenciId = ogrenciId;
    }

    public int getBolumId() {
        return bolumId;
    }

    public void setBolumId(int bolumId) {
        this.bolumId = bolumId;
    }

    public String getOgrNo() {
        return ogrNo;
    }

    public void setOgrNo(String ogrNo) {
        this.ogrNo = ogrNo;
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

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }
    
    @Override
    public String toString() {
        return ogrNo;
    }
}
