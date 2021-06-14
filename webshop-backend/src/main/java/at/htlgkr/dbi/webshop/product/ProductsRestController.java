package at.htlgkr.dbi.webshop.product;

import at.htlgkr.dbi.webshop.BaseController;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductsRestController extends BaseController {

    private final ProductService productService;

    @Autowired
    public ProductsRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductResource>> getProducts() {
        return response(productService.getProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ProductResource> getProduct(@PathVariable int id) {
        ProductResource product = productService.getProductById(id);
        return response(product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<ProductResource> deleteProduct(@PathVariable int id) {
        ProductResource productResource = productService.deleteProduct(id);
        return response(productResource);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductResource> addProduct(@RequestBody ProductDTO productDTO) {
        ProductResource productResource = productService.addProduct(productDTO);
        return response(productResource);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO){
        ProductResource productResource = productService.updateProduct(id, productDTO);
        return response(productResource);
    }

}
