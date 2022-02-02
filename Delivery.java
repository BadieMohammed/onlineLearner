package de.unidue.inf.is.domain;

public class Delivery {
    private int aID;
    private String abgabeText;

    public Delivery(int aID, String abgabeText) {
        this.aID = aID;
        this.abgabeText = abgabeText;
    }

    public Delivery() {
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public String getAbgabeText() {
        return abgabeText;
    }

    public void setAbgabeText(String abgabeText) {
        this.abgabeText = abgabeText;
    }
}
