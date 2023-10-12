package org.tienda.dao;

import org.tienda.model.Manufacturer;
import org.tienda.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductDAO {
    List<String> findAllNameProducts();
    Map<String, Double> priceAndNameProducts();
    List<Product> listBetweenRanges(double low, double hight);
    List<Product> listAllLaptops();
    Product findChepestProduct();
    Product findProductByCode(int code);
    void insertProduct(Product product);
    void updateProduct(Product product);
}
