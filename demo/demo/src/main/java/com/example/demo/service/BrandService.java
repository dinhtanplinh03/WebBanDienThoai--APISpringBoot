package com.example.demo.service;

import com.example.demo.model.Brand;
import com.example.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    // Lấy danh sách tất cả thương hiệu
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    // Lấy thương hiệu theo ID
    public Optional<Brand> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    // Thêm mới thương hiệu
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    // Cập nhật thương hiệu
    public Brand updateBrand(Integer id, Brand brandDetails) {
        return brandRepository.findById(id).map(brand -> {
            brand.setBrandName(brandDetails.getBrandName());
            return brandRepository.save(brand);
        }).orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    // Xóa thương hiệu
    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }
}
