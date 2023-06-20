package dev.cami.pottencial.techtest.service;

import dev.cami.pottencial.techtest.model.Order;
import dev.cami.pottencial.techtest.model.Seller;

public interface IOrderService {
  Order create(Seller seller);
  Order findById(Long id);
  Order update(Order order, int status);
}
