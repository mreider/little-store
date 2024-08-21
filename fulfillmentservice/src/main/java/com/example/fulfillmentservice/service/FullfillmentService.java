package com.example.fulfillmentservice.service;

import com.example.fulfillmentservice.model.Order;
import com.example.fulfillmentservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class FullfillmentService {
    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveOrder(Order order) {
        System.out.println("Received order to fulfill: " + order.getId());
        order.setFulfilled(true);
        orderRepository.save(order);
        System.out.println("Order fulfilled: " + order.getId());
    }
}