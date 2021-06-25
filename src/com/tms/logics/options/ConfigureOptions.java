package com.tms.logics.options;

import com.tms.entity.Town;
import com.tms.entity.transport.Transport;
import com.tms.logics.changer.DataStorage;
import com.tms.logics.changer.TownsChanger;
import com.tms.logics.changer.TransportChanger;
import com.tms.logics.solver.ConsolePrinter;
import com.tms.logics.storage.DbTownStorage;
import com.tms.logics.storage.DbTransportStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class ConfigureOptions {
    DbTownStorage dbTownStorage;
    DbTransportStorage dbTransportStorage;
    DataStorage changer;

    public ConfigureOptions(DbTownStorage dbTownStorage, DbTransportStorage dbTransportStorage) {
        this.dbTownStorage = dbTownStorage;
        this.dbTransportStorage = dbTransportStorage;
    }

    public void show(){
        Scanner sc = new Scanner(System.in);
        int choose=0;

        while (choose!=3){
            ConsolePrinter.printConfigureMenu();
            choose = sc.nextInt();
            if(choose==1){
                changer = new TownsChanger(dbTownStorage);
                changer.show();
            } else if(choose==2) {
                changer = new TransportChanger(dbTransportStorage);
                changer.show();
            } else break;
        }
    }
}
