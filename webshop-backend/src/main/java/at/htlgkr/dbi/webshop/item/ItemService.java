package at.htlgkr.dbi.webshop.item;

import at.htlgkr.dbi.webshop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProductService productService;

    @Autowired
    public ItemService(ItemRepository itemRepository, ProductService productService) {
        this.itemRepository = itemRepository;
        this.productService = productService;
    }

    @PostConstruct
    private void initItems() {
        itemRepository.save(new Item(null, productService.getProductByIdLocal(2), 3));
        itemRepository.save(new Item(null, productService.getProductByIdLocal(3), 4));
        itemRepository.save(new Item(null, productService.getProductByIdLocal(1), 1));
        itemRepository.save(new Item(null, productService.getProductByIdLocal(2), 2));
        itemRepository.save(new Item(null, productService.getProductByIdLocal(1), 2));
        itemRepository.save(new Item(null, productService.getProductByIdLocal(3), 1));
    }

    public Item addItem(Item item) {
        if (item != null) return itemRepository.save(item);
        else return null;
    }

    public Item getItemById(int id) {
        return itemRepository.findById(id).orElse(null);
    }
}
