package com.tms.logics.options;

import com.tms.entity.Town;
import com.tms.entity.transport.Transport;
import com.tms.logics.cache.TownsReader;
import com.tms.logics.cache.TownsSaver;
import com.tms.logics.cache.TransportReader;
import com.tms.logics.cache.TransportSaver;
import com.tms.logics.solver.ConsolePrinter;
import com.tms.logics.solver.Solver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainOptions {
    public static void showMainOptions(){
        ArrayList<Town> towns = TownsReader.read();
        ArrayList<Transport> transport = TransportReader.read();
        ConfigureOptions opt = new ConfigureOptions(towns,transport);
        int userInput=0;
        Scanner sc = new Scanner(System.in);

        while(userInput!=3){

            System.out.println("1-найти путь");
            System.out.println("2-управление ресурсами");
            System.out.println("3-выйти");
            userInput = sc.nextInt();

            if(userInput==1){

                System.out.println("Доступные города: ");
                for(Town t : towns){
                    System.out.print(t.getName()+" ");
                }
                System.out.println();


                String myTownOne = sc.nextLine();
                while (!checkTown(myTownOne,towns)){
                    System.out.print("Введите отправную точку: ");
                    myTownOne = sc.nextLine();
                }

                System.out.print("Введите конечную точку: ");
                String myTownTwo = sc.nextLine();
                while (!checkTown(myTownTwo,towns)){
                    System.out.print("Введите конечную точку:");
                    myTownTwo = sc.nextLine();
                }
                String finalMyTownOne = myTownOne;
                Town townOne = towns.stream().filter((t)->t.getName().equals(finalMyTownOne)).collect(Collectors.toList()).get(0);
                String finalMyTownTwo = myTownTwo;
                Town townTwo = towns.stream().filter((t)->t.getName().equals(finalMyTownTwo)).collect(Collectors.toList()).get(0);
                double distance = Solver.findDistance(townOne,townTwo);
                //код для нахождения пути между двумя городами
                System.out.print("Количество людей: ");
                int people = sc.nextInt();
                System.out.print("Количество груза в тоннах: ");
                int load = (int) Math.round(sc.nextDouble());
                Transport cheapest = Solver.calculateCheapest(transport, people, load, townOne, townTwo);
                ConsolePrinter.printCheapest(cheapest,distance,people,load,townOne,townTwo);
                Transport fastest = Solver.calculateFastest(transport, people, load, townOne, townTwo);
                ConsolePrinter.printFastest(fastest,distance,people,load,townOne,townTwo);
            } else if(userInput==2){
                opt.show();
            } else{
                TownsSaver.save(towns);
                TransportSaver.save(transport);
            }
        }

    }

    public static boolean checkTown(String name, ArrayList<Town> towns){
        return towns.stream().anyMatch((town -> town.getName().equals(name)));
    }
}