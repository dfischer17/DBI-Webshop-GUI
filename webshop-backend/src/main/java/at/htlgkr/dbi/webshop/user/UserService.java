package at.htlgkr.dbi.webshop.user;

import at.htlgkr.dbi.webshop.item.Item;
import at.htlgkr.dbi.webshop.item.ItemService;
import at.htlgkr.dbi.webshop.order.Order;
import at.htlgkr.dbi.webshop.order.OrderDTO;
import at.htlgkr.dbi.webshop.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final ItemService itemService;
    private final UserRepository userRepository;
    private final OrderService orderService;

    @Autowired
    public UserService(ItemService itemService, UserRepository userRepository, OrderService orderService) {
        this.itemService = itemService;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @PostConstruct
    private void initItems() {
        List<Order> ordersUser1 = new ArrayList<Order>();
        ordersUser1.add(orderService.getOrderByIdLocal(1));
        List<Order> ordersUser2 = new ArrayList<Order>();
        ordersUser2.add(orderService.getOrderByIdLocal(2));

        List<Item> cartUser1 = new ArrayList<>();
        cartUser1.add(itemService.getItemById(5));

        List<Item> cartUser2 = new ArrayList<>();
        cartUser2.add(itemService.getItemById(6));

        userRepository.save(new User(null, "dfischer17", "Daniel", "Fischer", "banane32@gmx.at", ordersUser1, cartUser1));
        userRepository.save(new User(null, "pfroehler17", "Pepe", "Fröhler", "pfroehler17@sus.htl-grieskirchen.at", ordersUser2, cartUser2));
    }

    public UserResource getUserById(int id) {

        Optional<User> optionalOrder = userRepository.findById(id);
        return optionalOrder.map(this::userToUserResource).orElse(null);
    }

    public User getUserByIdLocal(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserResource> getUsers() {
        return userRepository.findAll().stream().map(this::userToUserResource).collect(Collectors.toList());
    }

    private UserResource userToUserResource(User user) {
        return new UserResource(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getOrders(), user.getCart());
    }

    public UserResource deleteUser(int id) {
        User user = getUserByIdLocal(id);
        if (user == null) return null;

        userRepository.delete(user);
        return this.userToUserResource(user);
    }

    public UserResource addUser(UserDTO userDTO) {
        if (!userDTO.getUsername().isBlank() && !userDTO.getEmail().isBlank()) {
            User user = new User(null, userDTO.getUsername(), userDTO.getFirstname(), userDTO.getLastname(), userDTO.getEmail(), new ArrayList<>(), new ArrayList<>());
            userRepository.save(user);
            return this.userToUserResource(user);
        }
        return null;
    }

    public List<Item> getCartForUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) return user.getCart();
        else return null;
    }

    public List<Order> getOrdersForUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) return user.getOrders();
        else return null;
    }

    public Item deleteCartItem(int id, int itemId) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        List<Item> cart = user.getCart();
        Item toDelete = itemService.getItemById(itemId);
        cart.remove(toDelete);

        userRepository.save(user);

        return toDelete;
    }

    public UserResource updateUser(int id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        return this.userToUserResource(user);
    }

    public UserResource addItemToCart(int id, Item cartItem) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        Item newItem = itemService.addItem(cartItem);
        if (cartItem != null) {
            user.getCart().add(newItem);
            userRepository.save(user);
        } else
            return null;
        return this.userToUserResource(user);
    }

    public UserResource updateItemInCart(int userId, Item cartItem) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;
        Item item = user.getCart().stream().filter(x -> x.getId().equals(cartItem.getId())).findFirst().orElse(null);
        item.setQuantity(cartItem.getQuantity());
        userRepository.save(user);
        return this.userToUserResource(user);
    }

    public UserResource orderCart(int id, OrderDTO orderDTO) {
        Order newOrder = orderService.addOrder(orderDTO);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        user.getCart().clear();
        user.getOrders().add(newOrder);
        userRepository.save(user);
        return this.userToUserResource(user);
    }
}
