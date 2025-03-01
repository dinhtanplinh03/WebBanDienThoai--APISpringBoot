package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Lấy danh sách sản phẩm trong giỏ hàng của người dùng
    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getCartByUser(@PathVariable int userId) {
        List<Cart> cartItems = cartService.getCartByUser(userId);
        if (cartItems != null && !cartItems.isEmpty()) {
            return ResponseEntity.ok(cartItems);
        }
        return ResponseEntity.noContent().build();
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestParam int userId,
            @RequestParam int productId,
            @RequestParam int quantity) {
        Cart cartItem = cartService.addToCart(userId, productId, quantity);
        if (cartItem != null) {
            return ResponseEntity.ok(cartItem);
        }
        return ResponseEntity.badRequest().build();
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/update/{cartId}")
    public ResponseEntity<Cart> updateCartItem(
            @PathVariable int cartId,
            @RequestParam int quantity) {
        Cart updatedCart = cartService.updateCartItem(cartId, quantity);
        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        }
        return ResponseEntity.notFound().build();
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable int cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.ok().build();
    }

    // Xóa toàn bộ giỏ hàng của người dùng
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable int userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
