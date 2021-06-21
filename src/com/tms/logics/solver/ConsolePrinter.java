package com.tms.logics.solver;

import com.tms.entity.Town;
import com.tms.entity.transport.Transport;

public class ConsolePrinter {
    public static void printCheapest(Transport t, double distance, int people, int load, Town one, Town two){
        if(t!=null){
            System.out.printf("Самый дешевый транспорт для доставки "+ people + " человек и "+load+"т груза\n" +
                    "из " + one.getName()+ " в "+ two.getName()+" - это "+ t.getName()+". Время в пути %.2f"+
                    "ч. Cтоимость %.2f$.\n",distance/t.getSpeed(),+t.getPricePerKm()*distance);
        } else printLackOfTransport();

    }

    public static void printFastest(Transport t, double distance, int people, int load, Town one, Town two){
        if(t!=null){
            System.out.printf("Самый быстрый транспорт для доставки "+ people + " человек и "+load+"т груза\n" +
                    "из " + one.getName()+ " в "+ two.getName()+" - это "+ t.getName()+". Время в пути %.2f"+
                    "ч. Cтоимость %.2f$.\n",distance/t.getSpeed(),+t.getPricePerKm()*distance);
        } else printLackOfTransport();

    }

    private static void printLackOfTransport(){
        System.out.println("К сожалению, у нас пока нету транспорта для такой перевозки.");
    }
}
