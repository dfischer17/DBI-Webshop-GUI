package at.htlgkr.dbi.webshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void initItems() {
        productRepository.save(new Product(null, "Kaffee", 1.02, "Kaffee mit Milch"));
        productRepository.save(new Product(null, "Wurstsemmel", 1.50, "Wurstsemmmel mit Gurkerl"));
        productRepository.save(new Product(null, "Leberkäsesemmel", 0, "Ohne Gurkerl"));
    }

    public ProductResource getProductById(int id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(this::productToProductResource).orElse(null);
    }

    public Product getProductByIdLocal(int id) {

        return productRepository.findById(id).orElse(null);
    }

    public List<ProductResource> getProducts() {
        return productRepository.findAll().stream().map(this::productToProductResource).collect(Collectors.toList());
    }

    private ProductResource productToProductResource(Product product) {
        return new ProductResource(product.getId(), product.getName(), product.getPrice(), product.getDescription());
    }

    public ProductResource deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
            return this.productToProductResource(product);
        } else return null;
    }

    public ProductResource addProduct(ProductDTO productDTO) {
        if (!productDTO.getName().isBlank() && !(productDTO.getPrice() < 0)) {
            Product product = new Product(null, productDTO.getName(), productDTO.getPrice(), productDTO.getDescription());
            productRepository.save(product);
            return this.productToProductResource(product);
        } else return null;
    }

    public ProductResource updateProduct(int id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) return null;
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        productRepository.save(product);
        return this.productToProductResource(product);
    }
}
