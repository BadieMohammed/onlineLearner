package de.unidue.inf.is.domain;

public final class User {

    private String name;
    private String email;
    private int bNummer;

    public User(String email, String name) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getbNummer() {
        return bNummer;
    }

    public void setbNummer(int bNummer) {
        this.bNummer = bNummer;
    }
}