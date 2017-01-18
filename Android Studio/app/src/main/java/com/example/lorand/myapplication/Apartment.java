package com.example.lorand.myapplication;

/**
 * Created by Lorand on 17.01.2017.
 */

public class Apartment {

    private String title;
    private String address;
    private String nrCam;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNrCam() {
        return nrCam;
    }

    public void setNrCam(String nrCam) {
        this.nrCam = nrCam;
    }

    @Override
    public String toString() {
        return "Ap: " + title + '\'' +
                ", " + address + '\'' +
                ", " + nrCam +
                " camere";
    }

    public Apartment(String title, String address, String nrCam) {
        this.title = title;
        this.address = address;
        this.nrCam = nrCam;
    }


}
