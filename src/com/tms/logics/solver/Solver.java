package com.tms.logics.solver;

import com.tms.entity.Town;
import com.tms.entity.transport.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solver {

    public static double findDistance(Town town1, Town town2){
        double A = Math.toRadians(town1.getLatitude());
        double B = Math.toRadians(town2.getLatitude());
        double lA = Math.toRadians(town1.getLongitude());
        double lB = Math.toRadians(town2.getLongitude());

        double cosD = Math.sin(A)*Math.sin(B)+Math.cos(A)*Math.cos(B)*Math.cos(lA-lB);
        return  Math.acos(cosD)*6371;
    }

    public static Transport calculateCheapest(ArrayList<Transport> list, int people, int load, Town one, Town two){
        ArrayList<Transport> cheapList = new ArrayList<>();
        cheapList.add(calculateAirCheapest(list, people, load));
        cheapList.add(calculateSeaCheapest(list, people, load));
        cheapList.add(calculateLandCheapest(list, people, load));
        List<Transport> finalList = cheapList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        int min = finalList.size()!=0 ? finalList.get(0).getPricePerKm() : 1_000_000;
        Transport tr = null;
        for(Transport t : finalList){
            if(t.getPricePerKm()<=min){
                tr = t;
            }
        }
        return  tr;
    }

    public static Transport calculateFastest(ArrayList<Transport> list, int people, int load, Town one, Town two){
        ArrayList<Transport> cheapList = new ArrayList<>();
        cheapList.add(calculateAirFastest(list, people, load));
        cheapList.add(calculateSeaFastest(list, people, load));
        cheapList.add(calculateLandFastest(list, people, load));
        List<Transport> finalList = cheapList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        int min = finalList.size()!=0 ? finalList.get(0).getSpeed() : 0;
        Transport tr = null;
        for(Transport t : finalList){
            if(t.getSpeed()>=min){
                tr = t;
            }
        }
        return  tr;
    }

    private static Transport calculateAirCheapest(ArrayList<Transport> list, int people, int load){
        Transport cur = null;
        List<Transport> availableAirTransport = list.stream().filter((transport -> transport.getPeopleCapacity() >= people
                && transport.getLoadCapacity() >= load && transport.getType().equals("air"))).collect(Collectors.toList());
        return getTransportCheapest(cur, availableAirTransport);
    }

    private static Transport calculateSeaCheapest(ArrayList<Transport> list, int people, int load){
        Transport cur = null;
        List<Transport> availableSeaTransport = list.stream().filter((transport -> transport.getPeopleCapacity() >= people
                && transport.getLoadCapacity() >= load && transport.getType().equals("sea"))).collect(Collectors.toList());
        return getTransportCheapest(cur, availableSeaTransport);
    }

    private static Transport calculateLandCheapest(ArrayList<Transport> list, int people, int load){
        Transport cur = null;
        List<Transport> availableLandTransport = list.stream().filter((transport -> transport.getPeopleCapacity() >= people
                && transport.getLoadCapacity() >= load && transport.getType().equals("land"))).collect(Collectors.toList());
        return getTransportCheapest(cur, availableLandTransport);
    }

    private static Transport calculateAirFastest(ArrayList<Transport> list, int people, int load){
        Transport cur = null;
        List<Transport> availableAirTransport = list.stream().filter((transport -> transport.getPeopleCapacity() >= people
                && transport.getLoadCapacity() >= load && transport.getType().equals("air"))).collect(Collectors.toList());
        return getTransportFastest(cur, availableAirTransport);
    }

    private static Transport calculateSeaFastest(ArrayList<Transport> list, int people, int load){
        Transport cur = null;
        List<Transport> availableSeaTransport = list.stream().filter((transport -> transport.getPeopleCapacity() >= people
                && transport.getLoadCapacity() >= load && transport.getType().equals("sea"))).collect(Collectors.toList());
        return getTransportFastest(cur, availableSeaTransport);
    }

    private static Transport calculateLandFastest(ArrayList<Transport> list, int people, int load){
        Transport cur = null;
        List<Transport> availableLandTransport = list.stream().filter((transport -> transport.getPeopleCapacity() >= people
                && transport.getLoadCapacity() >= load && transport.getType().equals("land"))).collect(Collectors.toList());
        return getTransportFastest(cur, availableLandTransport);
    }

    private static Transport getTransportCheapest(Transport cur, List<Transport> availableTransport) {
        if (!availableTransport.isEmpty()) {
            int min = availableTransport.get(0).getPricePerKm();
            int i = 0;
            int j = 0;
            for (Transport t : availableTransport) {
                if (t.getPricePerKm() < min) {
                    min = t.getPricePerKm();
                    j=i;
                }
                i++;
            }
            cur = availableTransport.get(j);
        }
        return cur;
    }

    private static Transport getTransportFastest(Transport cur, List<Transport> availableTransport) {
        if (!availableTransport.isEmpty()) {
            int min = availableTransport.get(0).getSpeed();
            int i = 0;
            int j = 0;
            for (Transport t : availableTransport) {
                if (t.getSpeed() < min) {
                    min = t.getSpeed();
                    j=i;
                }
                i++;
            }
            cur = availableTransport.get(j);
        }
        return cur;
    }
}

