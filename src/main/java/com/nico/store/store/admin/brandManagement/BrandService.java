package com.nico.store.store.admin.brandManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private IBrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.orElse(null);
    }

    public void updateBrand(Long id, Brand updatedBrand) {
        Optional<Brand> existingBrand = brandRepository.findById(id);
        if (existingBrand.isPresent()) {
            Brand brandToUpdate = existingBrand.get();
            brandToUpdate.setName(updatedBrand.getName());
            // Cập nhật các trường khác nếu cần
            brandRepository.save(brandToUpdate);
        }
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
    public void addBrand(Brand brand) {
        brandRepository.save(brand);
    }
}