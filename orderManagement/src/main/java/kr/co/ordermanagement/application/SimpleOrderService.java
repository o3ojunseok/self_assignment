package kr.co.ordermanagement.application;

import kr.co.ordermanagement.domain.order.Order;
import kr.co.ordermanagement.domain.order.OrderRepository;
import kr.co.ordermanagement.domain.product.Product;
import kr.co.ordermanagement.domain.product.ProductRepository;
import kr.co.ordermanagement.presentation.dto.ChangeStateRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderProductRequestDto;
import kr.co.ordermanagement.presentation.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleOrderService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleOrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto createOrder(List<OrderProductRequestDto> orderProductRequestDtoList) {
        List<Product> orderProducts = makeOrderProducts(orderProductRequestDtoList);
        decreaseProductsAmount(orderProducts);

        Order order = new Order(orderProducts);
        orderRepository.add(order);

        OrderResponseDto orderResponseDto = OrderResponseDto.toDto(order);
        return orderResponseDto;
    }

    private List<Product> makeOrderProducts(List<OrderProductRequestDto> orderProductRequestDtoList) {
        return orderProductRequestDtoList
                .stream()
                .map(orderProductRequestDto -> {
                    Long productId = orderProductRequestDto.getId();
                    Product product = productRepository.findById(productId);

                    Integer orderedAmount = orderProductRequestDto.getAmount();
                    product.checkEnoughAmount(orderedAmount);

                    return new Product(
                            productId,
                            product.getName(),
                            product.getPrice(),
                            orderProductRequestDto.getAmount()
                    );
                }).toList();
    }

    private void decreaseProductsAmount(List<Product> orderedProducts) {
        orderedProducts
                .forEach(orderedProduct ->  {
                    Long productId = orderedProduct.getId();
                    Product product = productRepository.findById(productId);

                    Integer orderedAmount = orderedProduct.getAmount();
                    product.decreaseAmount(orderedAmount);

                    productRepository.update(product);
                });
    }

    public OrderResponseDto findById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        OrderResponseDto orderResponseDto = OrderResponseDto.toDto(order);
        return orderResponseDto;
    }

    public OrderResponseDto changeState(Long orderId, ChangeStateRequestDto changeStateRequestDto) {
        Order order = orderRepository.findById(orderId);
        String state = changeStateRequestDto.getState();

        order.changeStateForce(state);
        // orderRepository.update(order);
        OrderResponseDto orderResponseDto = OrderResponseDto.toDto(order);
        return orderResponseDto;
    }

    public List<OrderResponseDto> findByState(String state) {
        List<Order> orders = orderRepository.findByState(state);

        List<OrderResponseDto> orderResponseDtos = orders
                .stream()
                .map(OrderResponseDto::toDto)
                .toList();
        return orderResponseDtos;
    }
}
