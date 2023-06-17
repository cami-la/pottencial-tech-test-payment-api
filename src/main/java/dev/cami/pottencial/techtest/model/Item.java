package dev.cami.pottencial.techtest.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Item {
  private Long id;
  private Product product;
  private int quantity;
}
