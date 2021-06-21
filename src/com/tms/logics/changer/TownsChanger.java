package com.tms.logics.changer;

import com.tms.entity.Town;
import com.tms.logics.cache.TownsSaver;

import java.util.ArrayList;
import java.util.Scanner;

public class TownsChanger implements DataChangerInterface{
    ArrayList<Town> towns;
    Scanner sc = new Scanner(System.in);

    public TownsChanger(ArrayList<Town> towns){
        this.towns = towns;
    }

    @Override
    public void add() {
        System.out.print("ID города: ");
        int id = sc.nextInt();
        if(towns.stream().anyMatch(t -> t.getId() == id)) return;
        System.out.print("Название города: ");
        String name = sc.nextLine();
        System.out.print("Долгота: ");
        double longitude = sc.nextDouble();
        System.out.print("Широта: ");
        double latitude = sc.nextDouble();
        System.out.print("Наличие аэропорта(да,нет):");
        boolean hasAirport = sc.nextLine().equals("да");
        System.out.print("Наличие морского порта(да,нет):");
        boolean hasPort = sc.nextLine().equals("да");
        towns.add(new Town(id, name, longitude, latitude, hasAirport, hasPort));
        TownsSaver.save(towns);
    }

    @Override
    public void delete() {
        System.out.print("ID города: ");
        int id = sc.nextInt();
        if(towns.stream().noneMatch(t -> t.getId() == id)) return;
        int index=0;
        for(int i = 0; i < towns.size(); i++){
            if(towns.get(i).getId()==id){
                index=i;
            }
        }
        towns.remove(index);
        TownsSaver.save(towns);
    }

    @Override
    public void show() {
        int input=0;
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
