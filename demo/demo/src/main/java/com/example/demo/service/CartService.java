package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // Lấy danh sách sản phẩm trong giỏ hàng của người dùng
    public List<Cart> getCartByUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(cartRepository::findByUser).orElse(null);
    }

    // Thêm sản phẩm vào giỏ hàng
    public Cart addToCart(long userId, int productId, int quantity) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Product> product = productRepository.findById(productId);

        if (user.isPresent() && product.isPresent()) {
            Cart cartItem = new Cart();
            cartItem.setUser(user.get());
            cartItem.setProduct(product.get());
            cartItem.setQuantity(quantity);
            return cartRepository.save(cartItem);
        }
        return null;
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public Cart updateCartItem(int cartId, int quantity) {
        Optional<Cart> cartItem = cartRepository.findById(cartId);
        if (cartItem.isPresent()) {
            Cart cart = cartItem.get();
            cart.setQuantity(quantity);
            return cartRepository.save(cart);
        }
        return null;
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    public void removeFromCart(int cartId) {
        cartRepository.deleteById(cartId);
    }

    // Xóa toàn bộ giỏ hàng của người dùng
    public void clearCart(long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(cartRepository::deleteByUser);
    }
}
