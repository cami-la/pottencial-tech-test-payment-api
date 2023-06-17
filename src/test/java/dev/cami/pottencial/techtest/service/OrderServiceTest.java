package dev.cami.pottencial.techtest.service;

import dev.cami.pottencial.techtest.enummeration.Status;
import dev.cami.pottencial.techtest.model.Item;
import dev.cami.pottencial.techtest.model.Order;
import dev.cami.pottencial.techtest.model.Seller;
import dev.cami.pottencial.techtest.repository.OrderRepository;
import dev.cami.pottencial.techtest.service.impl.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
  @Mock
  private OrderRepository orderRepository;
  @InjectMocks
  private OrderService orderService;

  @Test
  void shouldCreateOrder() {
    //given
    Seller sellerFake = new Seller();
    List<Item> itensFake = Arrays.asList(new Item());
    Order orderFake = builderOrder();
    orderFake.setSeller(sellerFake);
    //when
    Mockito.when(orderRepository.save(ArgumentMatchers.any(Order.class)))
        .thenReturn(orderFake);
    //then
    Order actual = orderService.create(sellerFake, itensFake);
    Assertions.assertThat(actual).isSameAs(orderFake);
  }

  @Test
  void shouldFindOrderById() {
    //given
    Long idFake = 1L;
    Order orderFake = builderOrder();
    //when
    Mockito.when(orderRepository.findById(ArgumentMatchers.eq(idFake)))
        .thenReturn(Optional.of(orderFake));
    //then
    Order actual = orderService.findById(idFake);
    Assertions.assertThat(actual).isSameAs(orderFake);
  }

  @Test
  void shouldDoesntFindOrderById() {
    //given
    Long idFake = 1L;
    //when
    Mockito.when(orderRepository.findById(ArgumentMatchers.eq(idFake)))
        .thenReturn(Optional.empty());
    //then
    Assertions.assertThatThrownBy(() -> orderService.findById(idFake))
        .isInstanceOf(NoSuchElementException.class)
        .hasMessage(String.format("Order '%s' not found", idFake));
  }


  private Order builderOrder() {
    return Order.builder()
        .id(1L)
        .seller(new Seller())
        .date(LocalDate.now())
        .status(Status.AWAITING_PAYMENT)
        .build();
  }
}
