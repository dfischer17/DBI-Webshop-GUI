package at.htlgkr.dbi.webshop.item;

import at.htlgkr.dbi.webshop.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
  //  @Column(nullable = false)
    private Product product;
    @Column(nullable = false)
    private int quantity;
}
