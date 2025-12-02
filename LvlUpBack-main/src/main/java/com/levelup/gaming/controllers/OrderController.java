package com.levelup.gaming.controllers;

import com.levelup.gaming.models.Order;
import com.levelup.gaming.repositories.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(orderRequest.getUserId());
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setShippingPrice(orderRequest.getShippingPrice());
        order.setPaid(false);
        order.setStatus("Pendiente");
        order.setCreatedAt(LocalDateTime.now());

        List<com.levelup.gaming.models.OrderItem> orderItems = orderRequest.getItems().stream().map(itemReq -> {
            com.levelup.gaming.models.OrderItem item = new com.levelup.gaming.models.OrderItem();
            item.setProductId(itemReq.getProduct().getId());
            item.setName(itemReq.getProduct().getName());
            item.setPrice(itemReq.getProduct().getPrice());
            item.setQty(itemReq.getQuantity());
            item.setImage(itemReq.getProduct().getImageUrl());
            return item;
        }).toList();

        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @Data
    static class OrderRequest {
        private String userId;
        private List<OrderItemRequest> items;
        private com.levelup.gaming.models.Address shippingAddress;
        private String paymentMethod;
        private double totalPrice;
        private double shippingPrice;
    }

    @Data
    static class OrderItemRequest {
        private ProductRequest product;
        private int quantity;
    }

    @Data
    static class ProductRequest {
        private String id;
        private String name;
        private double price;
        private String imageUrl;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Autowired
    private com.levelup.gaming.repositories.UserRepository userRepository;

    @GetMapping("/myorders")
    public List<Order> getMyOrders(java.security.Principal principal) {
        if (principal == null) {
            return List.of();
        }
        String email = principal.getName();
        var user = userRepository.findByEmail(email).orElseThrow();
        return orderRepository.findByUserId(user.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String id,
            @RequestBody OrderStatusUpdateDTO statusUpdate) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(statusUpdate.getStatus());
                    if (statusUpdate.isPaid()) {
                        order.setPaid(true);
                        order.setPaidAt(LocalDateTime.now());
                    }
                    if ("Entregado".equals(statusUpdate.getStatus())) {
                        order.setDeliveredAt(LocalDateTime.now());
                    }
                    orderRepository.save(order);
                    return ResponseEntity.ok(order);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Data
    static class OrderStatusUpdateDTO {
        private String status;
        private boolean paid;
    }
}
