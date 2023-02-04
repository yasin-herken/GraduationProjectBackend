package graduationproject.backend.User.controller;

import graduationproject.backend.Product.payload.request.ProductDTO;
import graduationproject.backend.User.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "0") Integer page
    ){
        return sellerService.getProducts(pageSize,sortBy,direction,page);
    }

}
