package dev.cami.pottencial.techtest.service;

import dev.cami.pottencial.techtest.model.Item;
import dev.cami.pottencial.techtest.model.Order;
import dev.cami.pottencial.techtest.model.Seller;

import java.util.List;

public interface IOrderService {
  Order create(Seller seller, List<Item> itens);
  Order findById(Long id);
  Order update(Order order, int status);
}
