package com.tms.logics.cache;

import com.tms.entity.Town;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TownsSaver {
    public static void save(ArrayList<Town> towns){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("towns.txt"))) {
            for(Town t:towns){
                bw.write(t.toString()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
