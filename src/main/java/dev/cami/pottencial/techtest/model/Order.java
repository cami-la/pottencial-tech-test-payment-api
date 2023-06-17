package dev.cami.pottencial.techtest.model;

import dev.cami.pottencial.techtest.enummeration.Status;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Order {
  private Long id;
  @OneToOne
  private Seller seller;
  private Instant date;
  @OneToMany
  private List<Item> itens;
  @Enumerated
  private Status status;
}
