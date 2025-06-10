package com.example.demo.baseball.Model;



public class PitchResult {
    private String pitchSummary;
    private String batterReaction;
    private String outcome;

    public PitchResult() {} // 預設建構子，Spring 需要

    public PitchResult(String pitchSummary, String batterReaction, String outcome) {
        this.pitchSummary = pitchSummary;
        this.batterReaction = batterReaction;
        this.outcome = outcome;
    }

    public String getPitchSummary() {
        return pitchSummary;
    }

    public void setPitchSummary(String pitchSummary) {
        this.pitchSummary = pitchSummary;
    }

    public String getBatterReaction() {
        return batterReaction;
    }

    public void setBatterReaction(String batterReaction) {
        this.batterReaction = batterReaction;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}



