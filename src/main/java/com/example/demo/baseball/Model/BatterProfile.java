package com.example.demo.baseball.Model;

public class BatterProfile {
    private String name;
    private String specialty; // 例如 "擅長揮擊外角變化球"

    public BatterProfile(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
}