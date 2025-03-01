package com.example.demo.controller;

import com.example.demo.model.OrderDetail;
import com.example.demo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Lấy danh sách tất cả chi tiết đơn hàng
    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    // Lấy chi tiết đơn hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable int id) {
        try {
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
            return ResponseEntity.ok(orderDetail);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Tạo chi tiết đơn hàng mới
    @PostMapping
    public ResponseEntity<Void> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.createOrderDetail(orderDetail);
        return ResponseEntity.status(201).build(); // Trả về status code 201 (Created)
    }

    // Cập nhật chi tiết đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderDetail(@PathVariable int id, @RequestBody OrderDetail updatedOrderDetail) {
        try {
            orderDetailService.updateOrderDetail(id, updatedOrderDetail);
            return ResponseEntity.noContent().build(); // Trả về status code 204 (No Content)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa chi tiết đơn hàng theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int id) {
        try {
            orderDetailService.deleteOrderDetail(id);
            return ResponseEntity.noContent().build(); // Trả về status code 204 (No Content)
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Lấy danh sách chi tiết đơn hàng theo orderId
    @GetMapping("/order/{orderId}")
    public List<OrderDetail> getOrderDetailsByOrderId(@PathVariable int orderId) {
        return orderDetailService.getOrderDetailsByOrderId(orderId);
    }

    // Lấy danh sách chi tiết đơn hàng theo productId
    @GetMapping("/product/{productId}")
    public List<OrderDetail> getOrderDetailsByProductId(@PathVariable int productId) {
        return orderDetailService.getOrderDetailsByProductId(productId);
    }

    // Lấy danh sách chi tiết đơn hàng theo orderId và productId
    @GetMapping("/order/{orderId}/product/{productId}")
    public List<OrderDetail> getOrderDetailsByOrderIdAndProductId(@PathVariable int orderId, @PathVariable int productId) {
        return orderDetailService.getOrderDetailsByOrderIdAndProductId(orderId, productId);
    }
}
