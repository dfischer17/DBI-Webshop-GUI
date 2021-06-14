package at.htlgkr.dbi.webshop.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProductDTO {
    private String name;
    private double price;
    private String description;
}
