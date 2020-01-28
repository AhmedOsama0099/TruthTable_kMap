package com.example.truthtablek_map;

import java.util.ArrayList;

public class SingleTone {
    public static ArrayList<String>letters;
    public static ArrayList<String>row;
    public static ArrayList<String>num;

    public static ArrayList<String> getNum() {
        return num;
    }

    public static void setNum(ArrayList<String> num) {
        SingleTone.num = num;
    }

    SingleTone(){}

    public static void setLetters(ArrayList<String> letters) {
        SingleTone.letters = letters;
    }

    public static void setRow(ArrayList<String> row) {
        SingleTone.row = row;
    }



    public static ArrayList<String> getLetters() {
        return letters;
    }

    public static ArrayList<String> getRow() {
        return row;
    }


}
