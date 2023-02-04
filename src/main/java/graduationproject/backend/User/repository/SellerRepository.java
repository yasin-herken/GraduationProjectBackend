package graduationproject.backend.User.repository;

import graduationproject.backend.User.entity.Seller;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends PagingAndSortingRepository<Seller, Long>, JpaSpecificationExecutor<Seller> {

    Optional<Seller> findByUser_Id(Long id);

}
