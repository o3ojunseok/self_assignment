package kr.co.ordermanagement.presentation.dto;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.State;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<ProductDto> orderedProducts;
    private Integer totalPrice;
    private State state;

    public OrderResponseDto(Long id, List<ProductDto> orderedProducts, Integer totalPrice, State state) {
        this.id = id;
        this.orderedProducts = orderedProducts;
        this.totalPrice = totalPrice;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public List<ProductDto> getOrderedProducts() {
        return orderedProducts;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public State getState() {
        return state;
    }

    public static OrderResponseDto toDto(Order order) {
        List<ProductDto> orderedProducts = order.getOrderedProducts()
                .stream()
                .map(orderedProduct -> ProductDto.toDto(orderedProduct))
                .toList();

        OrderResponseDto orderREsponseDto = new OrderResponseDto(
                order.getId(),
                orderedProducts,
                order.getTotalPrice(),
                order.getState()
        );
        return orderREsponseDto;
    }
}
