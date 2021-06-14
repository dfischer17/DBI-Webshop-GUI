package at.htlgkr.dbi.webshop.user;

import at.htlgkr.dbi.webshop.item.Item;
import at.htlgkr.dbi.webshop.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResource {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private List<Order> orders;
    private List<Item> cart;
}
