package graduationproject.backend.Product.repository;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Product.entity.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findByIdAndCategory(Long id, Category category);
}
