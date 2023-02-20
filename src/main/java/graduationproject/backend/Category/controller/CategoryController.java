package graduationproject.backend.Category.controller;

import graduationproject.backend.Category.entity.Category;
import graduationproject.backend.Category.service.CategoryService;
import graduationproject.backend.Exception.controller.ResourceAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {

        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<Category> getCategory(@PathVariable String categoryName) {

        return ResponseEntity.ok(categoryService.findCategoryByName(categoryName));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody String category) throws ResourceAlreadyExists {

        return categoryService.createCategory(category);
    }

}
