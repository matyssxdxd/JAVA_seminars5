package lv.venta.seminars_5.service.impl;

import lv.venta.seminars_5.model.Product;
import lv.venta.seminars_5.service.ICRUDProductService;
import lv.venta.seminars_5.service.IFilterProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService {

    private ArrayList<Product> allProducts = new ArrayList<>(
            Arrays.asList(
                    new Product("Abols", "Sarkans", 0.99f, 5),
                    new Product("Zemene", "Salda", 1.23f, 3),
                    new Product("Arbuzs", "Roza", 3.99f, 2)));

    @Override
    public Product createProduct(Product product) throws Exception {
        if (product == null) throw new Exception("There is no products provided.");

        for (Product tempP : allProducts) {
            if (tempP.getTitle().equals(product.getTitle()) &&
                    tempP.getDescription().equals(product.getDescription()) &&
                    tempP.getPrice() == product.getPrice()) {
                tempP.setQuantity(tempP.getQuantity() + product.getQuantity());
                return tempP;
            }
        }

        Product newProduct = new Product(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
        allProducts.add(newProduct);
        return newProduct;
    }

    @Override
    public ArrayList<Product> retrieveAll() throws Exception {
        if (allProducts.isEmpty()) throw new Exception("There are no products.");
        return allProducts;
    }

    @Override
    public Product retrieveById(int id) throws Exception {
        if (allProducts.isEmpty()) throw new Exception("There are no products.");
        if (id < 0) throw new Exception("Invalid ID.");

        for (Product tempP : allProducts) {
            if (tempP.getId() == id) {
                return tempP;
            }
        }

        throw new Exception("There is no product with ID: " + id);
    }

    @Override
    public void updateById(int id, Product product) throws Exception {
        Product updateProduct = retrieveById(id);

        updateProduct.setTitle(product.getTitle());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct.setPrice(product.getPrice());
    }

    @Override
    public Product deleteById(int id) throws Exception {
        Product deleteProduct = retrieveById(id);
        allProducts.remove(deleteProduct);
        return deleteProduct;
    }

    @Override
    public ArrayList<Product> filterProduct(float price) throws Exception {
        if(price< 0 || price> 10000) throw new Exception("Wrong price threshold");

        ArrayList<Product> filteredProducts = new ArrayList<>();
        for(Product tempP: allProducts)
        {
            if(tempP.getPrice() <= price) {
                filteredProducts.add(tempP);
            }
        }

        return filteredProducts;
    }

    @Override
    public ArrayList<Product> filterProductByQuantityThreshold(int quantityThreshold) throws Exception {
        if(quantityThreshold < 0 || quantityThreshold > 100) throw new Exception("Wrong quantity threshold");

        ArrayList<Product> filteredProducts = new ArrayList<>();
        for(Product tempP: allProducts)
        {
            if(tempP.getQuantity() <= quantityThreshold) {
                filteredProducts.add(tempP);
            }
        }

        return filteredProducts;
    }

    @Override
    public ArrayList<Product> filterByTitleOrDescription(String searchText) throws Exception {
        if(searchText == null) throw new Exception("Wrong search text");
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for(Product tempP: allProducts)
        {
            if(tempP.getTitle().contains(searchText) ||
                    tempP.getDescription().contains(searchText)) {
                filteredProducts.add(tempP);
            }
        }

        return filteredProducts;
    }
}
