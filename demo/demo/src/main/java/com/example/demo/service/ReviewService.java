package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Lấy tất cả đánh giá
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Lấy đánh giá theo productId
    public List<Review> getReviewsByProductId(int productId) {
        return reviewRepository.findByProduct_ProductId(productId);
    }

    // Lấy đánh giá theo userId
    public List<Review> getReviewsByUserId(int userId) {
        return reviewRepository.findByUser_UserId(userId);
    }

    // Lấy đánh giá theo productId và userId
    public List<Review> getReviewByProductIdAndUserId(int productId, int userId) {
        return reviewRepository.findByProduct_ProductIdAndUser_UserId(productId, userId);
    }

    // Tạo mới một đánh giá
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    // Cập nhật đánh giá
    public Review updateReview(int reviewId, Review reviewDetails) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        return reviewRepository.save(review);
    }

    // Xóa đánh giá
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
