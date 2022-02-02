package de.unidue.inf.is.domain;

public class HandIn {
    private int bNummer;
    private int aNummer;
    private int aID;
    private int kID;

    public HandIn(int bNummer, int aNummer, int aID, int kID) {
        this.bNummer = bNummer;
        this.aNummer = aNummer;
        this.aID = aID;
        this.kID = kID;
    }

    public int getbNummer() {
        return bNummer;
    }

    public void setbNummer(int bNummer) {
        this.bNummer = bNummer;
    }

    public int getaNummer() {
        return aNummer;
    }

    public void setaNummer(int aNummer) {
        this.aNummer = aNummer;
    }

    public int getaID() {
        return aID;
    }

    public void setaID(int aID) {
        this.aID = aID;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }
}
