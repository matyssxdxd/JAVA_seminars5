package lv.venta.seminars_5.service.impl;

import lv.venta.seminars_5.model.Product;
import lv.venta.seminars_5.repo.IProductRepo;
import lv.venta.seminars_5.service.ICRUDProductService;
import lv.venta.seminars_5.service.IFilterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService {

    @Autowired
    private IProductRepo productRepo;

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
        if (productRepo.count() == 0) throw new Exception("There are no products.");
        return (ArrayList<Product>) productRepo.findAll();
    }

    @Override
    public Product retrieveById(int id) throws Exception {
        if (productRepo.count() == 0) throw new Exception("There are no products.");
        if (id < 0) throw new Exception("Invalid ID.");
        if (!productRepo.existsById(id)) throw new Exception("There is no product with ID: " + id);

        return productRepo.findById(id).get();
    }

    @Override
    public void updateById(int id, Product product) throws Exception {
        Product updateProduct = retrieveById(id);

        updateProduct.setTitle(product.getTitle());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct.setPrice(product.getPrice());
        productRepo.save(updateProduct);
    }

    @Override
    public Product deleteById(int id) throws Exception {
        Product deleteProduct = retrieveById(id);
        productRepo.delete(deleteProduct);
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
            if(tempP.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                    tempP.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
                filteredProducts.add(tempP);
            }
        }

        return filteredProducts;
    }

    @Override
    public float calculateTotalProductValue() throws Exception {
        if (allProducts.isEmpty()) throw new Exception("There are no products");

        float result = 0;

        for (Product tempP : allProducts) {
            result += tempP.getPrice() * tempP.getQuantity();
        }

        return result;
    }
}
