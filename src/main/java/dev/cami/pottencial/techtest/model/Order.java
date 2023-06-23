package dev.cami.pottencial.techtest.model;

import dev.cami.pottencial.techtest.enummeration.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "seller_id")
  private Seller seller;
  private LocalDate date;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
}
