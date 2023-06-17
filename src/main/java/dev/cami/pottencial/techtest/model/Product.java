package dev.cami.pottencial.techtest.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Product {
  private int id;
  private String description;
  private String value;
}
