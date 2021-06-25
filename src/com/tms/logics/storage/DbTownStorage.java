package com.tms.logics.storage;

import com.tms.entity.Town;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbTownStorage {

    Connection connection;

    public DbTownStorage(Connection connection) {
        this.connection = connection;
    }

    public List<Town> getAll() {
        List<Town> t = new ArrayList<>();
        try {
            ResultSet resultSet = connection.prepareStatement("select * from logistics.towns").executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");
                boolean airport = resultSet.getBoolean("airport");
                boolean seaport = resultSet.getBoolean("seaport");
                t.add(new Town(id, name, longitude, latitude, airport, seaport));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    public void deleteById(int id){
        try {
            connection.prepareStatement("delete from logistics.towns t where t.id ="+id).execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void add(Town town){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into logistics.towns values(default, ?,?,?,?,?)");
            preparedStatement.setString(1,town.getName());
            preparedStatement.setDouble(2,town.getLongitude());
            preparedStatement.setDouble(3,town.getLatitude());
            preparedStatement.setBoolean(4,town.isHasAirport());
            preparedStatement.setBoolean(5,town.isHasSeaport());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

