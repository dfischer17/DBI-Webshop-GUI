package at.htlgkr.dbi.webshop.order;

import at.htlgkr.dbi.webshop.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private int userId;
    private List<Item> products;
}
