package com.nico.store.store.admin.categoryManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public void updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category categoryToUpdate = existingCategory.get();
            categoryToUpdate.setName(updatedCategory.getName());
            // Cập nhật các trường khác nếu cần
            categoryRepository.save(categoryToUpdate);
        }
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}