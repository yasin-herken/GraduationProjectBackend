package graduationproject.backend.Product.controller;

import graduationproject.backend.Page.payload.request.PageRequestDTO;
import graduationproject.backend.Product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> getProducts(@RequestBody PageRequestDTO pageRequestDTO) {
        return productService.getProducts(pageRequestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable Long id
    ) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody int quantity){
        return productService.updateProduct(id, quantity);
    }
}
