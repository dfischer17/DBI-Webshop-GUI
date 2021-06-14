package at.htlgkr.dbi.webshop.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ProductResource {
    private int id;
    private String name;
    private double price;
    private String description;
}
