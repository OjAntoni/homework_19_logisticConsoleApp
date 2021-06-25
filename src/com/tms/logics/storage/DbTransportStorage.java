package com.tms.logics.storage;

import com.tms.entity.transport.Transport;
import com.tms.entity.transport.transport_type.AirTransport;
import com.tms.entity.transport.transport_type.LandTransport;
import com.tms.entity.transport.transport_type.SeaTransport;
import com.tms.entity.transport.transport_type.TransportType;
import com.tms.logics.changer.DataStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTransportStorage {

    private Connection connection;

    public DbTransportStorage(Connection connection) {
        this.connection = connection;
    }

    public List<Transport> getAll(){
        ArrayList<Transport> t = new ArrayList<>();
        try {
            ResultSet resultSet = connection.prepareStatement("select * from logistics.transport").executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int speed = resultSet.getInt("speed");
                int people = resultSet.getInt("people");
                int load = resultSet.getInt("load");
                TransportType type = null;
                switch (resultSet.getInt("type")) {
                    case 0 -> type = new LandTransport();
                    case 1 -> type = new AirTransport();
                    case 2 -> type = new SeaTransport();
                }
                int price = resultSet.getInt("price");
                t.add(new Transport(id, name, speed, people, load, type, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    public void deleteById(int id){
        try {
            connection.prepareStatement("delete from logistics.transport t where t.id="+id).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void add(Transport t){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into logistics.transport values (default , ?,?,?,?,?,?)");
            preparedStatement.setString(1,t.getName());
            preparedStatement.setInt(2,t.getSpeed());
            preparedStatement.setInt(3,t.getPeopleCapacity());
            preparedStatement.setInt(4,t.getLoadCapacity());
            preparedStatement.setInt(5,t.getIntType());
            preparedStatement.setInt(6,t.getPricePerKm());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
