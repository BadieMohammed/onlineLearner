package de.unidue.inf.is.domain;

public class CourseWithProducersName {
    private int kID;
    private String name;
    private String erstellersName;
    private int freiePlaetze;
    private String beschreibungsText;

    public CourseWithProducersName(int kID, String name, String erstellersName, int freiePlaetze) {
        this.name = name;
        this.freiePlaetze = freiePlaetze;
        this.erstellersName = erstellersName;
        this.kID = kID;
    }
    public CourseWithProducersName(int kID, String name, String erstellersName, int freiePlaetze, String beschreibungstext) {
        this.name = name;
        this.freiePlaetze = freiePlaetze;
        this.erstellersName = erstellersName;
        this.kID = kID;
        this.beschreibungsText = beschreibungstext;
    }

    public String getBeschreibungstext() {
        return beschreibungsText;
    }

    public void setBeschreibungstext(String beschreibungstext) {
        this.beschreibungsText = beschreibungstext;
    }

    public int getkID() {
        return kID;
    }

    public void setkID(int kID) {
        this.kID = kID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErstellersName() {
        return erstellersName;
    }

    public void setErstellersName(String erstellersName) {
        this.erstellersName = erstellersName;
    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(int freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }
}
