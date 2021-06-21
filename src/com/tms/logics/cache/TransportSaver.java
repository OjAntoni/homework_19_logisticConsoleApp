package com.tms.logics.cache;

import com.tms.entity.transport.Transport;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TransportSaver {
    public static void save(ArrayList<Transport> t){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("transport.txt"))) {
            for (Transport tr : t) {
                bw.write(tr.toStringForSave());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
