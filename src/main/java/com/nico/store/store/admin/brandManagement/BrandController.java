package com.nico.store.store.admin.brandManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/brandManagement")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public String showBrands(Model model) {
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);
        return "admin/brandTemplate/brandManagement";
    }

    @GetMapping("/{id}/edit")
    public String showEditBrandForm(@PathVariable Long id, Model model) {
        Brand brand = brandService.getBrandById(id);
        if (brand == null) {
            return "redirect:/admin/brandManagement";
        }
        model.addAttribute("brand", brand); // Đặt Brand vào request attribute
        return "admin/brandTemplate/editBrand";
    }

    @PostMapping("/{id}/update")
    public String updateBrand(@PathVariable Long id, @ModelAttribute Brand brand) {
        brandService.updateBrand(id, brand);
        return "redirect:/admin/brandManagement";
    }

    @PostMapping("/{id}/delete")
    public String deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return "redirect:/admin/brandManagement";
    }
    @GetMapping("/add")
    public String showAddBrandForm() {
        return "admin/brandTemplate/addBrand";
    }

    @PostMapping("/add")
    public String addBrand(@ModelAttribute Brand brand) {
        brandService.addBrand(brand);
        return "redirect:/admin/brandManagement";
    }

}