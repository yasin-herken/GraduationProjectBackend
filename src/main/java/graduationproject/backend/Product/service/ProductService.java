package graduationproject.backend.Product.service;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Category.repository.CategoryRepository;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import graduationproject.backend.Page.payload.request.PageRequestDTO;
import graduationproject.backend.Page.payload.response.PageResponse;
import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.Product.repository.ProductRepository;
import graduationproject.backend.User.mapper.ProductMapper;
import graduationproject.backend.User.payload.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static graduationproject.backend.Product.entity.specification.ProductSpecification.findByPageRequestDTO;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> getProducts(PageRequestDTO pageRequestDTO) {
        Pageable paging;
        if (pageRequestDTO.getDirection().equalsIgnoreCase("ASC")) {
            paging = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getPageSize(), Sort.by(Sort.Direction.ASC, pageRequestDTO.getSortBy()));
        } else {
            paging = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getPageSize(), Sort.by(Sort.Direction.DESC, pageRequestDTO.getSortBy()));
        }
        List<ProductResponse> productResponses = new ArrayList<>();
        productRepository.findAll(findByPageRequestDTO(pageRequestDTO.getCriteria()), paging).stream().forEach(
                child -> {
                    ProductResponse productResponse = ProductMapper.INSTANCE.mapToProductResponse(child);
                    productResponses.add(productResponse);
                }
        );
        PageResponse<ProductResponse> pageResponse = new PageResponse();
        pageResponse.setTotalRecords(productRepository.count());
        pageResponse.setData(productResponses);
        return ResponseEntity.ok(pageResponse);
    }

    public ResponseEntity<?> getProduct(String category, Long productId) {
        Category categoryDb = categoryRepository.findByName(category).orElseThrow(
                () -> new ResourceNotFoundException("Category not found")
        );
        Optional<Product> product = productRepository.findByIdAndCategory(productId, categoryDb);
        if (product.isPresent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.get().getId());
            productResponse.setTitle(product.get().getTitle());
            productResponse.setPrice(product.get().getPrice());
            productResponse.setCategory(product.get().getCategory());
            productResponse.setDescription(product.get().getDescription());
            productResponse.setImages(product.get().getImages());
            productResponse.setStock(product.get().getStock());
            productResponse.setColor(product.get().getColor());
            productResponse.setSize(product.get().getSize());
            return ResponseEntity.ok(productResponse);
        }
        return ResponseEntity.ok("Product not found");
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> updateProduct(Long id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
        return ResponseEntity.ok("Update product successfully");
    }


}
