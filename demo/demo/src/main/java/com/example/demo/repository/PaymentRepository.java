package com.example.demo.repository;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByTransactionId(String transactionId);

    // Thêm phương thức tìm thanh toán theo orderId
    List<Payment> findByOrder_OrderId(int orderId);

    // Thêm phương thức tìm thanh toán theo userId
    List<Payment> findByUser_UserId(int userId);
}
