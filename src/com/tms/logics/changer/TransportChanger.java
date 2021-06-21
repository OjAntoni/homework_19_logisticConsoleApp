package com.tms.logics.changer;

import com.tms.entity.transport.Transport;
import com.tms.entity.transport.transport_type.AirTransport;
import com.tms.entity.transport.transport_type.LandTransport;
import com.tms.entity.transport.transport_type.SeaTransport;
import com.tms.entity.transport.transport_type.TransportType;
import com.tms.logics.cache.TransportSaver;

import java.util.ArrayList;
import java.util.Scanner;

public class TransportChanger implements DataChangerInterface{
    ArrayList<Transport> transport;
    Scanner sc = new Scanner(System.in);

    public TransportChanger(ArrayList<Transport> transport) {
        this.transport = transport;
    }

    @Override
    public void add() {
        System.out.print("Название: ");
        String name = sc.nextLine();
        while (name.equals("")) name = sc.nextLine();
        System.out.print("ID транспорта: ");
        int id = Integer.parseInt(sc.next());
        if(transport.stream().anyMatch(t -> t.getId() == id)) return;

        System.out.print("Тип транспорта(air=1,land=2,sea=3): ");
        int typ = sc.nextInt();
        TransportType type;
        if(typ==1) type = new AirTransport();
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
        transport.add(new Transport(id,name,speed,peopleCapacity,loadCapacity,type,price));
        TransportSaver.save(transport);
    }

    @Override
    public void delete() {
        System.out.print("ID транспорта: ");
        int id = sc.nextInt();
        if(transport.stream().noneMatch(t -> t.getId() == id)) return;
        int j = 0;
        for(int i = 0; i < transport.size(); i++){
            if(transport.get(i).getId()==id) {
                j = i;
                break;
            }
        }
        transport.remove(j);
        TransportSaver.save(transport);
    }

    @Override
    public void show() {
        int input = 0;
        while (input!=3){
            System.out.println("1-добавить");
            System.out.println("2-удалить");
            System.out.println("3-назад");
            input = sc.nextInt();
            if(input==1){
                add();
            }else if (input==2){
                delete();
            }
        }
    }
}
