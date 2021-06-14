package at.htlgkr.dbi.webshop.user;

import at.htlgkr.dbi.webshop.item.Item;
import at.htlgkr.dbi.webshop.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String username;
    @Column()
    private String firstname;
    @Column()
    private String lastname;
    @Column(nullable = false)
    private String email;
    @OneToMany
    private List<Order> orders = new ArrayList<>();
    @OneToMany
    private List<Item> cart = new ArrayList<>();
}
