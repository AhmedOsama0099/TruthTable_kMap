package com.example.truthtablek_map;

import java.io.Serializable;

public class TruthTableModel  {
    private int decNum;
    public  Boolean[]binNum;
    private Boolean res;
    public TruthTableModel(){

    }
    public TruthTableModel(int decNum, Boolean[] binNum, Boolean res) {
        this.decNum = decNum;
        this.binNum = binNum;
        this.res = res;
        //System.out.println(":"+this.binNum[0]);
    }

    public int getDecNum() {
        return decNum;
    }

    public Boolean[] getBinNum() {
        return binNum;
    }

    public Boolean getRes() {
        return res;
    }
}
