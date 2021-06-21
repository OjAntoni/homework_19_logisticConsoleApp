package com.tms.logics.cache;

import com.tms.entity.Town;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TownsReader {
    public static ArrayList<Town> read(){
        ArrayList<Town> towns = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("towns.txt"));
            String town;
            while ((town=bf.readLine())!=null){
                String[] townChar = town.split(" ");
                towns.add(new Town(Integer.parseInt(townChar[0]), townChar[1], Double.parseDouble(townChar[2]),
                        Double.parseDouble(townChar[3]), townChar[4].equals("true"), townChar[5].equals("true")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return towns;
    }
}
