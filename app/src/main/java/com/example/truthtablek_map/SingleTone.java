package com.example.truthtablek_map;

import java.util.ArrayList;

public class SingleTone {
    public static ArrayList<String>letters;
    public static ArrayList<ArrayList<String>>tableData;



    SingleTone(){}

    public static void setLetters(ArrayList<String> letters) {
        SingleTone.letters = letters;
    }

    public static void setTableData() {
        SingleTone.tableData = new ArrayList<>();
    }



    public static ArrayList<String> getLetters() {
        return letters;
    }



}
