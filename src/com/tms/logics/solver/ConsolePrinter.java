package com.tms.logics.solver;

import com.tms.entity.Town;
import com.tms.entity.transport.Transport;

public class ConsolePrinter {

    public static void printCheapest(Transport t, double distance, int people, int load, Town one, Town two){
        System.out.printf("Самый дешевый транспорт для доставки "+ people + " человек и "+load+"т груза\n" +
                    "из " + one.getName()+ " в "+ two.getName()+" - это "+ t.getName()+". Время в пути %.2f"+
                    "ч. Cтоимость %.2f$.\n",distance/t.getSpeed(),+t.getPricePerKm()*distance);
    }

    public static void printFastest(Transport t, double distance, int people, int load, Town one, Town two){
        System.out.printf("Самый быстрый транспорт для доставки "+ people + " человек и "+load+"т груза\n" +
                    "из " + one.getName()+ " в "+ two.getName()+" - это "+ t.getName()+". Время в пути %.2f"+
                    "ч. Cтоимость %.2f$.\n",distance/t.getSpeed(),+t.getPricePerKm()*distance);
    }

    public static void printLackOfTransport(){
        System.out.println("К сожалению, у нас пока нету транспорта для такой перевозки.");
    }

    public static void printMainMenu(){
        System.out.println("1-найти путь");
        System.out.println("2-управление ресурсами");
        System.out.println("3-выйти");
    }

    public static void printConfigureMenu(){
        System.out.println("1-управление городами");
        System.out.println("2-управление транспортом");
        System.out.println("3-назад");
    }

    public static void printChangerMenu(){
        System.out.println("1-добавить");
        System.out.println("2-удалить");
        System.out.println("3-назад");
    }

    public static void printGoodbye(){
        System.out.println("Bye!!!");
    }
}
