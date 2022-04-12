package com.academy.demojavafx.controller;

import com.academy.demojavafx.dao.ProductDao;
import com.academy.demojavafx.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Ova klasa treba da bude šablon za kreiranje objekta koji će
 * posjedovati funkciju koja će učitati kolekciju objekata kreiranih po modelu.
 * <p>
 *     Što će nam ta kolekcija ???
 *
 *     <p>
 *         Nama ne treba. Potrebna je TableView komponenti.
 *     </p>
 * </p>
 */
public class ProductController {
    private final ProductDao productDao = new ProductDao();

    public ObservableList<Product> loadProducts(){
        List<Product> productList = productDao.getAll();
        ObservableList<Product> products = FXCollections.observableList(productList);
        return products;
    }

    public Product addProduct(Product product) {
        productDao.save(product);
        return product;
    }

    public void deleteSelectedProducts(List<Product> selectedProducts) {
        //DB delete
    }
}
