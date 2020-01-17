package me.kolganov.grannyshome.rest;

import lombok.RequiredArgsConstructor;
import me.kolganov.grannyshome.rest.dto.OrderDto;
import me.kolganov.grannyshome.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public List<OrderDto> getAllOrders() {
        return orderService.getAll().stream().map(OrderDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrderById(@PathVariable("id") long id) {
        return OrderDto.toDto(orderService.getById(id));
    }

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.save(OrderDto.toEntity(orderDto));
    }

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable("id") long id,
                            @RequestBody OrderDto orderDto) {
        orderService.update(OrderDto.toEntity(orderDto));
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable("id") long id) {
        orderService.delete(id);
    }
}