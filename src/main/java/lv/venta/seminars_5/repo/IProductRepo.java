package lv.venta.seminars_5.repo;

import lv.venta.seminars_5.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepo extends CrudRepository<Product, Integer> {

    Product findByTitleAndDescriptionAndPrice(String title, String description, float price);
}
