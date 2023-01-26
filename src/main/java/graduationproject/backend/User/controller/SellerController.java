package graduationproject.backend.User.controller;

import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.Product.payload.request.ProductDTO;
import graduationproject.backend.User.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    @Autowired
    private final SellerService sellerService;
    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody ProductDTO product){
        return sellerService.addNewProduct(product);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam Long sellerId){
        return sellerService.getProducts(sellerId);
    }

}
