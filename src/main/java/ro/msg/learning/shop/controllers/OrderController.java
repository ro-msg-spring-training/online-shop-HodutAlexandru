package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.msg.learning.shop.bf.OrderBf;
import ro.msg.learning.shop.models.dto.OrderDto;
import ro.msg.learning.shop.payload.order.OrderResponse;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderBf orderBf;

    @PostMapping("/new")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(this.orderBf.createOrder(orderDto));
    }

}
