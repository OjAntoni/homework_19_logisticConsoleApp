package com.tms.logics.cache;

import com.tms.entity.transport.Transport;
import com.tms.entity.transport.transport_type.AirTransport;
import com.tms.entity.transport.transport_type.LandTransport;
import com.tms.entity.transport.transport_type.SeaTransport;
import com.tms.entity.transport.transport_type.TransportType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransportReader {
    public static ArrayList<Transport> read(){
        ArrayList<Transport> t = new ArrayList<>();
        BufferedReader br;
            try {
                br = new BufferedReader(new FileReader("transport.txt"));
                String data;
                while ((data=br.readLine())!=null){
                    String[] elements = data.split(" ");
                    TransportType type=null;
                    switch (elements[5]) {
                        case "sea" -> type = new SeaTransport();
                        case "land" -> type = new LandTransport();
                        case "air" -> type = new AirTransport();
                    }
                    t.add(new Transport(Integer.parseInt(elements[0]),
                            elements[1].replaceAll("-"," "),Integer.parseInt(elements[2]),
                            Integer.parseInt(elements[3]), Integer.parseInt(elements[4]),
                            type, Integer.parseInt(elements[6])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return t;
    }

}
