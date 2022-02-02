package de.unidue.inf.is.domain;

public class Task {
    private int kID;
    private int aNummer;
    private String name;
    private String beschreibung;

    public Task(int kID, int aNummer, String name, String beschreibung) {
        this.kID = kID;
        this.aNummer = aNummer;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }

    public int getaNummer() {
        return aNummer;
    }

    public void setaNummer(int aNummer) {
        this.aNummer = aNummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
