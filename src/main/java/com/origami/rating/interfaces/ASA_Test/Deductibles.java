package com.origami.rating.interfaces.ASA_Test;

public class Deductibles extends Coinsurance {
    private String basisOfCalc;
    private Coinsurance group;
    
    public String getBasisOfCalc() {
        return basisOfCalc;
    }
    public void setBasisOfCalc(String basisOfCalc) {
        this.basisOfCalc = basisOfCalc;
    }
    public Coinsurance getGroup() {
        return group;
    }
    public void setGroup(Coinsurance group) {
        this.group = group;
    }
}
