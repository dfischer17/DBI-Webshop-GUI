package at.htlgkr.dbi.webshop.item;

import at.htlgkr.dbi.webshop.product.Product;

import javax.persistence.Column;
import javax.persistence.OneToOne;

public class ItemDto {
    private int productId;
    private int quantity;
}
