package graduationproject.backend.Product.entity.specification;

import graduationproject.backend.Product.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductSpecification {

    public static Specification<Product> findAll() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    public static Specification<Product> findByPageRequestDTO(Map<String, Map<String, Object>> criteria) {
        List<Predicate> predicateList = new ArrayList<>();
        return (root, query, cb) -> {
            if (criteria == null || criteria.isEmpty()) {
                return cb.conjunction();
            } else {
                Map<String, Object> eq = criteria.get("eq");
                if (eq != null) {
                    for (Object key : eq.keySet()) {
                        if (key.toString().equals("category")) {
                            predicateList.add(cb.equal(root.get(Product_.CATEGORY).get("name"), eq.get(key).toString()));
                        } else if (key.toString().equals("color")) {
                            predicateList.add(cb.equal(root.get(Product_.COLOR), Color.valueOf(eq.get(key).toString())));
                        } else if (key.toString().equals("title")) {
                            predicateList.add(cb.equal(root.get(Product_.TITLE), eq.get(key)));
                        } else if (key.toString().equals("size")) {
                             if (eq.get(key).toString().equals("[]")) {
                                return cb.conjunction();
                            }
                            List<String> productSizes = Arrays.asList(eq.get(key).toString().replaceAll("\\[", "").replaceAll("]", "").split(","));

                            List<ProductSize> productSizes1 = productSizes.stream().map(size -> size.trim()).map(ProductSize::valueOf).collect(Collectors.toList());
                            predicateList.add(cb.and(root.get(Product_.SIZE).in(productSizes1)));
                        } else if (key.toString().equals("gender")) {
                            predicateList.add(cb.equal(root.get(Product_.GENDER), Gender.valueOf(eq.get(key).toString())));
                        } else if(key.toString().equals("search")){
                            predicateList.add(cb.like(root.get(Product_.TITLE),"%" + eq.get(key).toString() + "%"));
                        }

                    }
                }
                Map rng = criteria.get("rng");
                if (rng != null) {
                    for (Object key : rng.keySet()) {
                        if (key.toString().equals("price")) {
                            List<String> price = Arrays.asList(rng.get(key).toString().replaceAll("\\[", "").replaceAll("]", "").split(","));
                            ;
                            predicateList.add(cb.between(root.get("price").get("price").as(Double.class), Double.valueOf(price.get(0)), Double.valueOf(price.get(1))));
                        }
                    }
                }
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }
}
