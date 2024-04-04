package lv.venta.seminars_5.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product {


    private int id;

    private static int counter;

    @NotNull
    @Pattern(regexp = "[A-Z]{1}[a-z ]+")
    @Size(min = 3, max = 20)
    private String title;

    @NotNull
    @Pattern(regexp = "[A-Za-z ]+")
    @Size(min = 4, max = 200)
    private String description;

    @Min(0)
    @Max(10000)
    private float price;

    @Min(0)
    @Max(100)
    private int quantity;

    public void setId() {
        id = counter++;
    }

    public Product(String title, String description, float price, int quantity) {
        setId();
        setTitle(title);
        setDescription(description);
        setPrice(price);
        setQuantity(quantity);
    }
}
