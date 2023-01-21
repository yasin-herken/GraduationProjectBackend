package graduationproject.backend.Category.service;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Category.repository.CategoryRepository;
import graduationproject.backend.Exception.controller.ResourceAlreadyExists;
import graduationproject.backend.Exception.controller.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findCategoryByName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(
                ()->new ResourceNotFoundException("Not found category by name " + categoryName)
        );
        return category;
    }

    public ResponseEntity<Category> createCategory(String category) throws ResourceAlreadyExists {
        Optional<Category> categoryDb = categoryRepository.findByName(category);
        if(categoryDb.isEmpty()){
            Category newCategory = new Category();
            newCategory.setParentid(null);
            newCategory.setName(category);
            categoryRepository.save(newCategory);
            return ResponseEntity.ok(newCategory);
        }
        throw new ResourceAlreadyExists("Already exists with name" + category);


    }

}
