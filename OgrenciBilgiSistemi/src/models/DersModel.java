package models;

public class DersModel {
    private int dersId;
    private int bolumId;
    private String adi;
    
    private BolumModel bolum;

    public DersModel(int dersId, int bolumId, String adi, BolumModel bolum) {
        this.dersId = dersId;
        this.bolumId = bolumId;
        this.adi = adi;
        this.bolum = null;
    }

    public int getDersId() {
        return dersId;
    }

    public void setDersId(int dersId) {
        this.dersId = dersId;
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
    
    public BolumModel getBolum() {
        return bolum;
    }
    
    public void setBolum(BolumModel bolum) {
        this.bolum = bolum;
    }
    
    @Override
    public String toString() {
        return adi;
    }
}
