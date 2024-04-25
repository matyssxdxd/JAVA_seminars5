package lv.venta.seminars_5.service;

import lv.venta.seminars_5.model.Product;

import java.util.ArrayList;

public interface IFilterProductService {

    public abstract ArrayList<Product> filterProduct(float price) throws Exception;
    public abstract ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception;
    public abstract ArrayList<Product> filterByTitleOrDescription(String searchText) throws Exception;
    public abstract float calculateTotalProductValue() throws Exception;
}
