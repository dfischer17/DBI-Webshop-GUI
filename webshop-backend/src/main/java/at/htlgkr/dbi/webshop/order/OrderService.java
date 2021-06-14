package at.htlgkr.dbi.webshop.order;

import at.htlgkr.dbi.webshop.item.Item;
import at.htlgkr.dbi.webshop.item.ItemService;
import at.htlgkr.dbi.webshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    @PostConstruct
    private void initItems() {
        List<Item> itemsOrder1 = new ArrayList<>();
        itemsOrder1.add(itemService.getItemById(1));
        itemsOrder1.add(itemService.getItemById(2));
        List<Item> itemsOrder2 = new ArrayList<>();
        itemsOrder2.add(itemService.getItemById(3));
        itemsOrder2.add(itemService.getItemById(1));
        //orderRepository.save(new Order(null, 1, itemsOrder1));
        //orderRepository.save(new Order(null, 2, itemsOrder2));
    }

    public OrderResource getOrderById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(this::orderToOrderResource).orElse(null);
    }

    public Order getOrderByIdLocal(int id) {

        return orderRepository.findById(id).orElse(null);
    }

    public List<OrderResource> getOrders() {
        return orderRepository.findAll().stream().map(this::orderToOrderResource).collect(Collectors.toList());
    }

    private OrderResource orderToOrderResource(Order order) {
        return new OrderResource(order.getId(), order.getUserId(), order.getItems());
    }

    public OrderResource deleteOrder(int id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            orderRepository.delete(order);
            return this.orderToOrderResource(order);
        } else return null;
    }

    public Order addOrder(OrderDTO orderDTO) {
        if (orderDTO.getUserId() > 0) {
            Order order = new Order(null, orderDTO.getUserId(), orderDTO.getProducts());
            return orderRepository.save(order);
        }
        return null;
    }
}
