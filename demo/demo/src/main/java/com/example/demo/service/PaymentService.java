package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Lấy danh sách tất cả thanh toán
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Lấy thanh toán theo ID
    public Payment getPaymentById(int id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            return payment.get();
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    // Tạo thanh toán mới
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Cập nhật thanh toán
    public Payment updatePayment(int id, Payment updatedPayment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            Payment payment = existingPayment.get();
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentMethod(updatedPayment.getPaymentMethod());
            payment.setStatus(updatedPayment.getStatus());
            payment.setTransactionId(updatedPayment.getTransactionId());
            return paymentRepository.save(payment);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    // Xóa thanh toán theo ID
    public void deletePayment(int id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            paymentRepository.delete(payment.get());
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    // Lấy danh sách thanh toán theo orderId
    public List<Payment> getPaymentsByOrderId(int orderId) {
        return paymentRepository.findByOrder_OrderId(orderId);
    }

    // Lấy danh sách thanh toán theo userId
    public List<Payment> getPaymentsByUserId(int userId) {
        return paymentRepository.findByUser_UserId(userId);
    }

    // Lấy thanh toán theo transactionId
    public Payment getPaymentByTransactionId(String transactionId) {
        return paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found with transactionId: " + transactionId));
    }
}
