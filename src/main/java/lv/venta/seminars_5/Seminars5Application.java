package lv.venta.seminars_5;

import lv.venta.seminars_5.model.Product;
import lv.venta.seminars_5.repo.IProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Seminars5Application {

    public static void main(String[] args) {
        SpringApplication.run(Seminars5Application.class, args);
    }

    @Bean
    public CommandLineRunner testDatabaseLayer(IProductRepo productRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product p1 = new Product("Abols", "Sarkans", 0.99f, 5);
                Product p2 = new Product("Zemene", "Salda", 1.23f, 3);
                Product p3 = new Product("Arbuzs", "Roza", 3.99f, 2);
                productRepo.save(p1);
                productRepo.save(p2);
                productRepo.save(p3);
            }
        };
    }
}
