package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // Lấy danh sách giỏ hàng theo người dùng
    List<Cart> findByUser(User user);

    // Xóa tất cả sản phẩm trong giỏ hàng theo user
    void deleteByUser(User user);
}
