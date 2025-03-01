package com.example.demo.repository;

import com.example.demo.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    // Tìm tất cả chi tiết đơn hàng theo orderId
    List<OrderDetail> findByOrderOrderId(int orderId);

    // Tìm tất cả chi tiết đơn hàng theo productId
    List<OrderDetail> findByProductProductId(int productId);

    // Tìm tất cả chi tiết đơn hàng theo orderId và productId
    List<OrderDetail> findByOrderOrderIdAndProductProductId(int orderId, int productId);
}
