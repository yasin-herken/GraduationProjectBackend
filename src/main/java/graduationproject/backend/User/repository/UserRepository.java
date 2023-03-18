package graduationproject.backend.User.repository;

import graduationproject.backend.User.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<CustomUser, Long>, JpaSpecificationExecutor<CustomUser> {
    Optional<CustomUser> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}