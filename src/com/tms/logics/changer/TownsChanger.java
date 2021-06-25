package com.tms.logics.changer;

import com.tms.entity.Town;

import com.tms.logics.solver.ConsolePrinter;
import com.tms.logics.storage.DbTownStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class TownsChanger implements DataStorage {
    DbTownStorage dbTownStorage;
    ArrayList<Town> towns;
    Scanner sc = new Scanner(System.in);

    public TownsChanger(DbTownStorage storage){
        dbTownStorage=storage;
        towns = ((ArrayList<Town>) storage.getAll());
    }

    @Override
    public void add() {
        System.out.print("ID города: ");
        int id = sc.nextInt();
        if(towns.stream().anyMatch(t -> t.getId() == id)) return;
        System.out.print("Название города: ");
        String name = sc.nextLine();
        while (name.equals("")) name = sc.nextLine();
        System.out.print("Долгота: ");
        double longitude = sc.nextDouble();
        System.out.print("Широта: ");
        double latitude = sc.nextDouble();
        System.out.print("Наличие аэропорта(да,нет):");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        boolean hasAirport = sc.nextLine().equals("да");
        System.out.print("Наличие морского порта(да,нет):");
        boolean hasPort = scanner.nextLine().equals("да");
        dbTownStorage.add(new Town(id, name, longitude, latitude, hasAirport, hasPort));
        //TownsSaver.save(towns);
    }

    @Override
    public void delete() {
        System.out.print("ID города: ");
        int id = sc.nextInt();
        if(dbTownStorage.getAll().stream().noneMatch(t -> t.getId() == id)) return;
        dbTownStorage.deleteById(id);
        towns = ((ArrayList<Town>) dbTownStorage.getAll());
    }

    @Override
    public void show() {
        int input=0;
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
