package org.tienda.dao;

import org.tienda.model.Manufacturer;
import org.tienda.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl extends ConnectionDB implements ProductDAO{
    @Override
    public List<String> findAllNameProducts(){
        String sql = "SELECT nombre FROM producto";
        List<String> list = new ArrayList<>();

        try {
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();


            String product = null;

            while(result.next()){
                product = result.getString(1);
                list.add(product);
            }
            closeConnectionDB();
            return list;
        } catch (SQLException e) {
            System.out.println("Error trying to get the products: " + e.getMessage());
            return list;
        }
    }

    @Override
    public Map<String, Double> priceAndNameProducts(){
        String sql = "SELECT nombre, precio FROM producto";
        Map<String, Double> mappedProducts = new HashMap<>();

        try {
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            String product = null;
            Double price = null;

            while(result.next()){
                product = result.getString(1);
                price = result.getDouble(2);
                mappedProducts.put(product, price);
            }
            closeConnectionDB();
            return mappedProducts;
        } catch (Exception e) {
            System.out.println("Error trying to get the products: " + e.getMessage());
            return mappedProducts;
        }
    }

    @Override
    public List<Product> listBetweenRanges(double lowerLimit, double upperLimit) {
        String sql = "SELECT * FROM producto WHERE precio BETWEEN ? AND ?";
        List<Product> productList = new ArrayList<>();

        try {
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, lowerLimit);
            ps.setDouble(2, upperLimit);

            ResultSet result = ps.executeQuery();

            Product product = null;

            while (result.next()){
                product = fillProduct(result);
                productList.add(product);
            }
            closeConnectionDB();
            return productList;
        } catch (SQLException e) {
            System.out.println("Error trying to get the products: " + e.getMessage());
            return productList;
        }

    }

    @Override
    public List<Product> listAllLaptops() {
        String sql = "SELECT * FROM producto WHERE nombre LIKE '%portatil%'";
        List<Product> productList = new ArrayList<>();

        try{
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            Product product = null;
            while (result.next()){
                product = fillProduct(result);
                productList.add(product);
            }
            closeConnectionDB();
            return productList;
        } catch (SQLException e) {
            System.out.println("Error trying to get the laptops: " + e.getMessage());
            return productList;
        }
    }

    @Override
    public Product findChepestProduct() {
        String sql = "SELECT * FROM producto WHERE precio = (SELECT MIN(precio) FROM producto)";
        Product product = null;

        try{
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet result = ps.executeQuery();

            while (result.next()){
                product = fillProduct(result);
            }
            closeConnectionDB();
            return product;
        } catch (Exception e) {
            System.out.println("Error trying to get the cheapest product: " + e.getMessage());
            return product;
        }
    }

    @Override
    public Product findProductByCode(int code) {
        String sql = "SELECT * FROM producto WHERE codigo = ?";
        Product product = null;

        try{
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, code);

            ResultSet result = ps.executeQuery();

            while (result.next()){
                product = fillProduct(result);
            }
            closeConnectionDB();
            return product;
        } catch (Exception e) {
            System.out.println("Error trying to get the product: " + e.getMessage());
            return product;
        }
    }


    @Override
    public void insertProduct(Product product){
        String sql = "INSERT INTO producto (codigo, nombre, precio, codigo_fabricante) VALUES (?, ?, ?, ?)";

        try {
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getFabricantCode());

            ps.executeUpdate();
            closeConnectionDB();
        } catch (Exception e) {
            System.out.println("Error trying to insert the product: " + e.getMessage());
        }
    }

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

    @Override
    public void updateProduct(Product product){
        String sql = "UPDATE producto SET codigo = ?, nombre = ?, precio = ?, codigo_fabricante = ? WHERE codigo = ?";

        try {
            openConnectionDB();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product.getCode());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getFabricantCode());
            ps.setInt(5, product.getCode());

            ps.executeUpdate();
            closeConnectionDB();
        } catch (Exception e) {
            System.out.println("Error trying to update the product: " + e.getMessage());
        }
    }

    public Product fillProduct(ResultSet result){
        Product product = new Product();

        try {
            product.setCode(result.getInt(1));
            product.setName(result.getString(2));
            product.setPrice(result.getDouble(3));
            product.setFabricantCode(result.getInt(4));
            return product;
        } catch (SQLException e) {
            System.out.println("Error trying to fill the product: " + e.getMessage());
            return product;
        }
    }

}
