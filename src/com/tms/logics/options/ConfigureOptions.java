package com.tms.logics.options;

import com.tms.entity.Town;
import com.tms.entity.transport.Transport;
import com.tms.logics.changer.DataChangerInterface;
import com.tms.logics.changer.TownsChanger;
import com.tms.logics.changer.TransportChanger;

import java.util.ArrayList;
import java.util.Scanner;

public class ConfigureOptions {
    ArrayList<Town> towns;
    ArrayList<Transport> transport;
    DataChangerInterface changer;

    public ConfigureOptions(ArrayList<Town> towns, ArrayList<Transport> transport) {
        this.towns = towns;
        this.transport = transport;
    }

    public void show(){
        Scanner sc = new Scanner(System.in);
        int choose=0;

        while (choose!=3){
            System.out.println("1-управление городами");
            System.out.println("2-управление транспортом");
            System.out.println("3-назад");
            choose = sc.nextInt();
            if(choose==1){
                changer = new TownsChanger(towns);
                changer.show();
            } else if(choose==2) {
                changer = new TransportChanger(transport);
                changer.show();
            } else break;
        }
    }
}
