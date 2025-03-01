package com.example.demo.repository;

import com.example.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Tìm các đánh giá theo productId
    List<Review> findByProduct_ProductId(int productId);

    // Tìm các đánh giá theo userId
    List<Review> findByUser_UserId(int userId);

    // Tìm đánh giá theo productId và userId
    List<Review> findByProduct_ProductIdAndUser_UserId(int productId, int userId);
}
