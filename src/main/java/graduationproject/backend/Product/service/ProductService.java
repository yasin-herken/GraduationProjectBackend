package graduationproject.backend.Product.service;

import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.Product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public ResponseEntity<Product> createProduct(Product product){

        Optional<Product> productDb = productRepository.findByTitle(product.getTitle());
        if(productDb.isPresent()){
            productDb.get().update(product);
            return ResponseEntity.ok(productRepository.save(productDb.get()));
        }
        return ResponseEntity.ok(productRepository.save(product));
    }
}
