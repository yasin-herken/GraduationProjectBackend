package graduationproject.backend.Role.repository;

import graduationproject.backend.Role.entity.ERole;
import graduationproject.backend.Role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}