package kr.co.ordermanagement.presentation.controller;

import kr.co.ordermanagement.application.SimpleOrderService;
import kr.co.ordermanagement.domain.order.State;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private SimpleOrderService simpleOrderService;
    @Autowired
    OrderController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody List<OrderProductRequestDto> orderProductRequestDtoList) {
        OrderResponseDto orderResponseDto = simpleOrderService.createOrder(orderProductRequestDtoList);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = simpleOrderService.findById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> changeOrderState(
            @PathVariable Long orderId,
            @RequestBody ChangeStateRequestDto changeStateRequestDto
    ) {
        if (changeStateRequestDto.getState().equals("CREATED") ||
        changeStateRequestDto.getState().equals("SHIPPING") ||
        changeStateRequestDto.getState().equals("COMPLETED") ||
        changeStateRequestDto.getState().equals("CANCELED")) {
            OrderResponseDto orderResponseDto = simpleOrderService.changeState(orderId, changeStateRequestDto);
            return ResponseEntity.ok(orderResponseDto);
        } else {
            throw new RuntimeException("존재하지 않는 상태");
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByState(@RequestParam State state) {
        List<OrderResponseDto> orderResponseDtos = simpleOrderService.findByState(state);
        return ResponseEntity.ok(orderResponseDtos);
    }

    @PatchMapping("/orders/{orderId}/cancel")
    public ResponseEntity<OrderResponseDto> cancelOrderById(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = simpleOrderService.cancelOrderById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }
}
