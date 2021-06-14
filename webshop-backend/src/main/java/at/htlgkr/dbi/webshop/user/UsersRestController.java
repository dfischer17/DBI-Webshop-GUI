package at.htlgkr.dbi.webshop.user;

import at.htlgkr.dbi.webshop.BaseController;
import at.htlgkr.dbi.webshop.item.Item;
import at.htlgkr.dbi.webshop.item.ItemDto;
import at.htlgkr.dbi.webshop.order.Order;
import at.htlgkr.dbi.webshop.order.OrderDTO;
import at.htlgkr.dbi.webshop.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersRestController extends BaseController {

    private final UserService userService;

    @Autowired
    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResource>> getUsers() {
        return response(userService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable int id) {
        UserResource userResource = userService.getUserById(id);
        return response(userResource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> addUser(@RequestBody UserDTO userDTO) {
        UserResource userResource = userService.addUser(userDTO);
        return response(userResource);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/cart")
    public ResponseEntity<UserResource> addItemToCart(@PathVariable int id, @RequestBody Item cartItem) {
        UserResource userResource = userService.addItemToCart(id, cartItem);
        return response(userResource);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/cart")
    public ResponseEntity<UserResource> updateItemInCart(@PathVariable int id, @RequestBody Item cartItem){
        UserResource userResource = userService.updateItemInCart(id, cartItem);
        return response(userResource);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/{id}/cart/order")
    public ResponseEntity<UserResource> orderCart(@PathVariable int id, @RequestBody OrderDTO orderDTO){
        UserResource userResource = userService.orderCart(id, orderDTO);
        return response(userResource);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/cart")
    public ResponseEntity<List<Item>> getCart(@PathVariable int id) {
        List<Item> cart = userService.getCartForUser(id);
        return response(cart);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/cart/{itemId}")
    public ResponseEntity<Item> deleteCartItem(@PathVariable int id, @PathVariable int itemId) {
        Item deletedItem = userService.deleteCartItem(id, itemId);

        return response(deletedItem);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<UserResource> deleteUser(@PathVariable int id) {
        UserResource userResource = userService.deleteUser(id);
        return response(userResource);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/orders")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable int id) {
        List<Order> orders = userService.getOrdersForUser(id);
        return response(orders);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO){
        UserResource updatedUser = userService.updateUser(id, userDTO);
        return response(updatedUser);
    }
}
