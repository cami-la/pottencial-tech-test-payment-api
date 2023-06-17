package dev.cami.pottencial.techtest.model;

import dev.cami.pottencial.techtest.enummeration.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @OneToOne
  private Seller seller;
  private LocalDate date;
  @OneToMany
  private List<Item> itens;
  @Enumerated
  private Status status;
}
