package dev.cami.pottencial.techtest.service.impl;

import dev.cami.pottencial.techtest.enummeration.Status;
import dev.cami.pottencial.techtest.model.Item;
import dev.cami.pottencial.techtest.model.Order;
import dev.cami.pottencial.techtest.model.Seller;
import dev.cami.pottencial.techtest.repository.OrderRepository;
import dev.cami.pottencial.techtest.service.IOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public record OrderService(
  OrderRepository orderRepository
) implements IOrderService {
  public static final String INVALID_TRANSITION_MESSAGE = "Invalid status transition.";
  @Override
  public Order create(Seller seller, List<Item> itens) {
    Order order = Order.builder()
        .seller(seller)
        .date(LocalDate.now())
        .itens(itens)
        .status(Status.AWAITING_PAYMENT)
        .build();
    return orderRepository.save(order);
  }

  @Override
  public Order findById(Long id) {
    return orderRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException(String.format("Order '%s' not found", id)));
  }

  @Override
  public Order update(Order order, int status) {
    int currentStatus = order.getStatus().ordinal();
    int newStatus = calculateNewStatus(currentStatus, status);
    order.setStatus(Status.values()[newStatus]);
    return orderRepository.save(order);
  }

  public int calculateNewStatus(int currentStatus, int status) {
    return switch (currentStatus) {
      case 0 -> // Aguardando pagamento
          calculateNewStatusForAwaitingPayment(status);
      case 1 -> // Pagamento Aprovado
          calculateNewStatusForPaymentApproved(status);
      case 2 -> // Enviado para Transportadora
          calculateNewStatusForSentToCarrier(status);
      default -> throw new IllegalArgumentException(INVALID_TRANSITION_MESSAGE);
    };
  }

  private int calculateNewStatusForAwaitingPayment(int status) {
    if (status == 1) {
      return 1; // Pagamento Aprovado
    } else if (status == 4) {
      return 4; // Cancelada
    } else {
      throw new IllegalArgumentException(INVALID_TRANSITION_MESSAGE);
    }
  }

  private int calculateNewStatusForPaymentApproved(int status) {
    if (status == 2) {
      return 2; // Enviado para Transportadora
    } else if (status == 4) {
      return 4; // Cancelada
    } else {
      throw new IllegalArgumentException(INVALID_TRANSITION_MESSAGE);
    }
  }

  private int calculateNewStatusForSentToCarrier(int status) {
    if (status == 3) {
      return 3; // Entregue
    } else {
      throw new IllegalArgumentException(INVALID_TRANSITION_MESSAGE);
    }
  }
}
