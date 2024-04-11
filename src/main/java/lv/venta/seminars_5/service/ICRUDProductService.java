package lv.venta.seminars_5.service;

import lv.venta.seminars_5.model.Product;

import java.util.ArrayList;

public interface ICRUDProductService {

    public abstract Product createProduct(Product product) throws Exception;
    public abstract ArrayList<Product> retrieveAll() throws Exception;
    public abstract Product retrieveById(int id) throws Exception;
    public abstract void updateById(int id, Product product) throws Exception;
    public abstract Product deleteById(int id) throws Exception;

}
