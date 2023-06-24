package models;

public class NotlarModel {
    private int notlarId;
    private int ogrenciId;
    private int dersId;
    private double vizeNotlar;
    private double finalNotlar;
    private double ortNotlar;

    public NotlarModel(int notlarId, int ogrenciId, int dersId, double vizeNotlar, double finalNotlar, double ortNotlar) {
        this.notlarId = notlarId;
        this.ogrenciId = ogrenciId;
        this.dersId = dersId;
        this.vizeNotlar = vizeNotlar;
        this.finalNotlar = finalNotlar;
        this.ortNotlar = ortNotlar;
    }

    public int getNotlarId() {
        return notlarId;
    }

    public void setNotlarId(int notlarId) {
        this.notlarId = notlarId;
    }

    public int getOgrenciId() {
        return ogrenciId;
    }

    public void setOgrenciId(int ogrenciId) {
        this.ogrenciId = ogrenciId;
    }

    public int getDersId() {
        return dersId;
    }

    public void setDersId(int dersId) {
        this.dersId = dersId;
    }

    public double getVizeNotlar() {
        return vizeNotlar;
    }

    public void setVizeNotlar(double vizeNotlar) {
        this.vizeNotlar = vizeNotlar;
    }

    public double getFinalNotlar() {
        return finalNotlar;
    }

    public void setFinalNotlar(double finalNotlar) {
        this.finalNotlar = finalNotlar;
    }

    public double getOrtNotlar() {
        return ortNotlar;
    }

    public void setOrtNotlar(double ortNotlar) {
        this.ortNotlar = ortNotlar;
    }
}

