package graduationproject.backend.User.service;

import graduationproject.backend.Admin.payload.request.SellerDTO;
import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Category.repository.CategoryRepository;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import graduationproject.backend.Product.entity.Img;
import graduationproject.backend.Product.entity.Product;
import graduationproject.backend.Product.payload.request.ProductDTO;
import graduationproject.backend.Product.repository.ProductRepository;
import graduationproject.backend.User.entity.CustomUser;
import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.repository.SellerRepository;
import graduationproject.backend.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Optional;
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
    public ResponseEntity<Product> addNewProduct(ProductDTO product) {

        Seller seller = sellerRepository.findById(product.getSellerId()).orElseThrow(
                () -> new UsernameNotFoundException("Seller not found with id: " + product.getSellerId())
        );
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
        newProduct.setImages(product.getImages().stream().map(img -> {
            Img newImg = new Img();
            newImg.setUrl(img);
            return newImg;
        }).collect(Collectors.toSet()));
        try{
            Product savedProduct = productRepository.save(newProduct);
            seller.add(savedProduct);
            Seller savedSeller = sellerRepository.save(seller);
            int size = savedSeller.getProducts().size();
            return new ResponseEntity<>(savedSeller.getProducts().get(size-1), HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getProducts(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(seller.getProducts(), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getSellers() {
        return ResponseEntity.ok(sellerRepository.findAll().stream().map(seller->{
            SellerDTO sellerDTO = new SellerDTO();
            sellerDTO.setId(seller.getId());
            sellerDTO.setCompanyName(seller.getCompanyName());
            sellerDTO.setAddress(seller.getAddress());
            sellerDTO.setCity(seller.getCity());
            sellerDTO.setPhone(seller.getPhone());
            return sellerDTO;
        }).collect(Collectors.toList()));
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
                if(f.get(sellerDTO) == null)
                    continue;
                Field field1 = seller.getClass().getDeclaredField(f.getName());
                field1.setAccessible(true);
                field1.set(seller,f.get(sellerDTO));
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
    public ResponseEntity<?> deleteSeller(Long id) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sellerRepository.delete(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
