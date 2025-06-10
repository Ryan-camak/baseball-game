package com.example.demo.baseball.Model;



public class PitchRequest {
    private String pitchType;
    private String pitchZone;
    private String pitchSpeed;
    private String batter;

    // Getters and Setters
    public String getPitchType() {
        return pitchType;
    }

    public void setPitchType(String pitchType) {
        this.pitchType = pitchType;
    }

    public String getPitchZone() {
        return pitchZone;
    }

    public void setPitchZone(String pitchZone) {
        this.pitchZone = pitchZone;
    }

    public String getPitchSpeed() {
        return pitchSpeed;
    }

    public void setPitchSpeed(String pitchSpeed) {
        this.pitchSpeed = pitchSpeed;
    }
    public String getBatter() {
    return batter;
}

    public void setBatter(String batter) {
        this.batter = batter;
}
}
