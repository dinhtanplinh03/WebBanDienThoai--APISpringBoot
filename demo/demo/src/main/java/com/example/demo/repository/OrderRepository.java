package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Tìm đơn hàng theo userId
    List<Order> findByUserId(int userId);

    // Tìm đơn hàng theo ID (JpaRepository có sẵn `findById` nhưng nó trả về Optional)
    default Order getById(int orderId) {
        return findById(orderId).orElse(null);
    }

    // Cập nhật trạng thái đơn hàng
    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.status = :status WHERE o.orderId = :orderId")
    void updateStatus(int orderId, String status);
}
