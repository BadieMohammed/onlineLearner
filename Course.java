package de.unidue.inf.is.domain;

public class Course {
    private String name;
    private int kID;
    private String beschreibungsText;
    private String einschreibeSchluessel;
    private int freiePlaetze;
    private int ersteller;

    public Course(String name) {
        this.name = name;
    }

    public Course(int kID, String name, String beschreibungsText, int freiePlaetze, int ersteller) {
        this.kID = kID;
        this.name = name;
        this.beschreibungsText = beschreibungsText;
        this.freiePlaetze = freiePlaetze;
        this.ersteller = ersteller;
    }

    public Course(String name, String beschreibungsText, String einschreibeSchluessel, int freiePlaetze, int ersteller) {
        this.name = name;
        this.beschreibungsText = beschreibungsText;
        this.einschreibeSchluessel = einschreibeSchluessel;
        this.freiePlaetze = freiePlaetze;
        this.ersteller = ersteller;
    }

    public Course(int kID, String name, String beschreibungsText, String einschreibeSchluessel, int freiePlaetze, int ersteller) {
        this.name = name;
        this.kID = kID;
        this.beschreibungsText = beschreibungsText;
        this.einschreibeSchluessel = einschreibeSchluessel;
        this.freiePlaetze = freiePlaetze;
        this.ersteller = ersteller;
    }

    public Course(int kID, String name, int ersteller, int freiePlaetze) {
        this.name = name;
        this.freiePlaetze = freiePlaetze;
        this.ersteller = ersteller;
        this.kID = kID;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }

    public String getBeschreibungsText() {
        return beschreibungsText;
    }

    public void setBeschreibungsText(String beschreibungsText) {
        this.beschreibungsText = beschreibungsText;
    }

    public String getEinschreibeSchluessel() {
        return einschreibeSchluessel;
    }

    public void setEinschreibeSchluessel(String einschreibeSchluessel) {
        this.einschreibeSchluessel = einschreibeSchluessel;
    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(int freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }

    public int getErsteller() {
        return ersteller;
    }

    public void setErsteller(int ersteller) {
        this.ersteller = ersteller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
