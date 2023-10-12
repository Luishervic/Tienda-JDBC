package org.tienda.dao;

import org.tienda.model.Manufacturer;

import java.sql.PreparedStatement;

public class ManufacturerDAOImpl extends ConnectionDB implements ManufacturerDAO{
    @Override
    public void insertManufacturer(Manufacturer manufacturer){
        String sql = "INSERT INTO fabricante (codigo,nombre) VALUES (?,?)";

        try {
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, manufacturer.getCode());
            ps.setString(2, manufacturer.getName());

            ps.executeUpdate();
            closeConnectionDB();
        } catch (Exception e) {
            System.out.println("Error trying to insert the manufacturer: " + e.getMessage());
        }

    }
}
