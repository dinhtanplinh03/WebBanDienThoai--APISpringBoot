package com.example.demo.service;

import com.example.demo.model.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    // Lấy danh sách tất cả chi tiết đơn hàng
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    // Lấy chi tiết đơn hàng theo ID
    public OrderDetail getOrderDetailById(int id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (orderDetail.isPresent()) {
            return orderDetail.get();
        } else {
            throw new RuntimeException("Order detail not found with id: " + id);
        }
    }

    // Tạo chi tiết đơn hàng mới
    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    // Cập nhật chi tiết đơn hàng
    public void updateOrderDetail(int id, OrderDetail updatedOrderDetail) {
        Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(id);
        if (existingOrderDetail.isPresent()) {
            OrderDetail orderDetail = existingOrderDetail.get();
            orderDetail.setQuantity(updatedOrderDetail.getQuantity());
            orderDetail.setPrice(updatedOrderDetail.getPrice());
            orderDetail.setOrder(updatedOrderDetail.getOrder());
            orderDetail.setProduct(updatedOrderDetail.getProduct());
            orderDetailRepository.save(orderDetail);
        } else {
            throw new RuntimeException("Order detail not found with id: " + id);
        }
    }

    // Xóa chi tiết đơn hàng theo ID
    public void deleteOrderDetail(int id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (orderDetail.isPresent()) {
            orderDetailRepository.delete(orderDetail.get());
        } else {
            throw new RuntimeException("Order detail not found with id: " + id);
        }
    }

    // Lấy danh sách chi tiết đơn hàng theo orderId
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        return orderDetailRepository.findByOrderOrderId(orderId);
    }

    // Lấy danh sách chi tiết đơn hàng theo productId
    public List<OrderDetail> getOrderDetailsByProductId(int productId) {
        return orderDetailRepository.findByProductProductId(productId);
    }

    // Lấy danh sách chi tiết đơn hàng theo orderId và productId
    public List<OrderDetail> getOrderDetailsByOrderIdAndProductId(int orderId, int productId) {
        return orderDetailRepository.findByOrderOrderIdAndProductProductId(orderId, productId);
    }
}
