package lv.venta.seminars_5.repo;

import lv.venta.seminars_5.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IProductRepo extends CrudRepository<Product, Integer> {

    Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

    ArrayList<Product> findByPriceLessThanEqual(float price);

    ArrayList<Product> findByQuantityLessThanEqual(int quantityThreshold);

    ArrayList<Product> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String searchText, String searchText1);

    @Query(nativeQuery = true, value = "SELECT SUM(PRICE * QUANTITY) FROM PRODUCT_TABLE")
    float calculateTotalValueOfDBProducts();

}
