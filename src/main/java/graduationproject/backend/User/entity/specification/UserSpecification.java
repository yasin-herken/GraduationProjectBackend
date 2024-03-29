package graduationproject.backend.User.entity.specification;

import graduationproject.backend.User.entity.Seller;
import graduationproject.backend.User.entity.Seller_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public UserSpecification() {
    }

    public static Specification<Seller> getAllUsers() {
        return (root, query, criteriaBuilder) -> {
            if (Long.class != query.getResultType()) {
                root.fetch(Seller_.USER);
            }
            return criteriaBuilder.conjunction();
        };
    }

}