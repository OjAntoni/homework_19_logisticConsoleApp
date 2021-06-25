package com.tms.logics.changer;

import com.tms.entity.transport.Transport;
import com.tms.entity.transport.transport_type.AirTransport;
import com.tms.entity.transport.transport_type.LandTransport;
import com.tms.entity.transport.transport_type.SeaTransport;
import com.tms.entity.transport.transport_type.TransportType;
import com.tms.logics.solver.ConsolePrinter;
import com.tms.logics.storage.DbTransportStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class TransportChanger implements DataStorage {
    ArrayList<Transport> transport;
    DbTransportStorage dbTransportStorage;
    Scanner sc = new Scanner(System.in);

    public TransportChanger(DbTransportStorage dbTransportStorage) {
        this.dbTransportStorage = dbTransportStorage;
        transport = ((ArrayList<Transport>) dbTransportStorage.getAll());
    }

    @Override
    public void add() {
        System.out.print("Название: ");
        String name = sc.nextLine();
        while (name.equals("")) name = sc.nextLine();
        System.out.print("ID транспорта: ");
        int id = Integer.parseInt(sc.next());
        if(transport.stream().anyMatch(t -> t.getId() == id)) return;

        System.out.print("Тип транспорта(land=1,air=2,sea=3): ");
        int typ = sc.nextInt();
        TransportType type;
        if(typ==2) type = new AirTransport();
        else if(typ==3) type = new SeaTransport();
        else type = new LandTransport();


        System.out.print("Скорость: ");
        int speed = sc.nextInt();
        System.out.print("Вместимость людей: ");
        int peopleCapacity = sc.nextInt();
        System.out.print("Вместимость груза в тоннах: ");
        int loadCapacity = sc.nextInt();
        System.out.print("Цена за км: ");
        int price = sc.nextInt();
        dbTransportStorage.add(new Transport(id,name,speed,peopleCapacity,loadCapacity,type,price));
        transport = ((ArrayList<Transport>) dbTransportStorage.getAll());
    }

    @Override
    public void delete() {
        System.out.print("ID транспорта: ");
        int id = sc.nextInt();
        if(transport.stream().noneMatch(t -> t.getId() == id)) return;
        dbTransportStorage.deleteById(id);
        transport = ((ArrayList<Transport>) dbTransportStorage.getAll());
        //TransportSaver.save(transport);
    }

    @Override
    public void show() {
        int input = 0;
        while (input!=3){
            ConsolePrinter.printChangerMenu();
            input = sc.nextInt();
            if(input==1){
                add();
            }else if (input==2){
                delete();
            }
        }
    }

}
