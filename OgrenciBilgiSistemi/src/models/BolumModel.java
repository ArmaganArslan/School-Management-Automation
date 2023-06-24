package models;

public class BolumModel {
    private int bolumId;
    private String adi;

    public BolumModel(int bolumId, String adi) {
        this.bolumId = bolumId;
        this.adi = adi;
    }

    public int getBolumId() {
        return bolumId;
    }

    public void setBolumId(int bolumId) {
        this.bolumId = bolumId;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }
    
    @Override
    public String toString() {
        return adi;
    }
}
