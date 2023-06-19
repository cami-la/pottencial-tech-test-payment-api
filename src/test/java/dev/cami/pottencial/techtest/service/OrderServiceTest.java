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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
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
    List<Item> itensFake = List.of(new Item());
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

  @ParameterizedTest
  @CsvSource({
      "0, 1",
      "0, 4",
      "1, 2",
      "1, 4",
      "2, 3"
  })
  void shouldUpdateOrder(int currentStatus, int newStatus) {
    //given
    Order orderFake = builderOrder();
    orderFake.setStatus(Status.values()[currentStatus]);
    Mockito.when(orderRepository.save(ArgumentMatchers.any(Order.class)))
        .thenReturn(orderFake);
    //when
    Order actual = orderService.update(orderFake, newStatus);
    Assertions.assertThat(actual).isSameAs(orderFake);
    Assertions.assertThat(actual.getStatus()).isSameAs(Status.values()[newStatus]);
  }
  @ParameterizedTest
  @CsvSource({
      "0, 1",
      "0, 4",
      "1, 2",
      "1, 4",
      "2, 3"
  })
  void shouldCalculateNewStatus(int currentStatus, int status) {
    //when
    int actual = orderService.calculateNewStatus(currentStatus, status);
    //then
    Assertions.assertThat(actual).isSameAs(status);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 3})
  void shouldThrowExceptionWhenCalculatingNewStatusWithInvalidCurrentStatus(int currentStatus) {
    //when + then
    Assertions.assertThatThrownBy(() -> orderService.calculateNewStatus(currentStatus, 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(OrderService.INVALID_TRANSITION_MESSAGE);
  }

  @ParameterizedTest
  @CsvSource({
      "0, 0",
      "0, 2",
      "0, 3",
      "1, 0",
      "1, 1",
      "1, 3",
      "2, 0",
      "2, 1",
      "2, 4"
  })
  void shouldThrowIllegalArgumentExceptionWhenCalculatingNewStatusWithInvalidStatus(int currentStatus, int status) {
    //when + then
    Assertions.assertThatThrownBy(() -> orderService.calculateNewStatus(currentStatus, status))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(OrderService.INVALID_TRANSITION_MESSAGE);
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
