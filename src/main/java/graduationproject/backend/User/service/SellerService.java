package graduationproject.backend.User.service;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Category.repository.CategoryRepository;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import graduationproject.backend.Page.payload.response.PageResponse;
import graduationproject.backend.Product.entity.Img;
import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.Product.payload.request.ProductDTO;
import graduationproject.backend.Product.repository.ProductRepository;
import graduationproject.backend.User.entity.CustomUser;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.mapper.ProductMapper;
import graduationproject.backend.User.payload.response.ProductResponse;
import graduationproject.backend.User.repository.SellerRepository;
import graduationproject.backend.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerService {

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public Seller createSeller(SellerDTO sellerDTO) {
        CustomUser user = userRepository.findById(sellerDTO.getId()).orElse(null);
        if (user == null) {
            return null;
        }
        return new Seller(user, sellerDTO.getCompanyName(), sellerDTO.getAddress(), sellerDTO.getCity(), sellerDTO.getPhone());
    }

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Transactional
    public ResponseEntity<?> addNewProduct(ProductDTO product) {

        Seller seller = getSeller();
        if (seller == null)
            throw new UsernameNotFoundException("Seller not found");

        seller.getProducts().stream().forEach(p -> {
            if (p.getTitle().equals(product.getTitle())) {
                throw new RuntimeException("Product already exists");
            }
        });

        Category category = categoryRepository.findByName(product.getCategory().getName()).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with name: " + product.getCategory().getName())
        );
        Product newProduct = new Product();
        newProduct.setCategory(category);
        newProduct.setProductDetails(product.getProductDetails());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setStock(product.getStock());
        newProduct.setImages(product.getImages().stream().map(img -> {
            Img newImg = new Img();
            newImg.setUrl(img);
            return newImg;
        }).collect(Collectors.toSet()));
        try {
            Product savedProduct = productRepository.save(newProduct);
            seller.add(savedProduct);
            sellerRepository.save(seller);
            ProductResponse responseProduct = ProductMapper.INSTANCE.mapToProductResponse(savedProduct);
            return new ResponseEntity<>(responseProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getProducts(Integer pageSize, String sortBy, String direction, Integer page) {
        Seller seller = getSeller();
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        int start = Math.min((int) pageable.getOffset(), seller.getProducts().size());
        int end = Math.min((start + pageable.getPageSize()), seller.getProducts().size());
        Page<Product> pageProducts = new PageImpl<>(seller.getProducts().subList(start, end), pageable, seller.getProducts().size());
        PageResponse<Product> response = new PageResponse<>();
        response.setData(pageProducts.getContent());
        response.setTotalRecords(pageProducts.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> updateSeller(Long id, SellerDTO sellerDTO) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Class<?> sellerDTOClass = sellerDTO.getClass();
        Field[] field = sellerDTOClass.getDeclaredFields();

        for (Field f : field) {
            f.setAccessible(true);
            try {
                if (f.get(sellerDTO) == null)
                    continue;
                Field field1 = seller.getClass().getDeclaredField(f.getName());
                field1.setAccessible(true);
                field1.set(seller, f.get(sellerDTO));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        if (sellerDTO.getId() != null) {
            seller.setId(sellerDTO.getId());
        }

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteProduct(Long id) {
        Seller seller = getSeller();
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        seller.getProducts().remove(product);
        sellerRepository.save(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Seller getSeller() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userPrincipal = (UserDetailsImpl) auth.getPrincipal();
        return sellerRepository.findByUser_Id(userPrincipal.getId()).orElse(null);
    }

}
