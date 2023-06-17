package dev.cami.pottencial.techtest.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seller {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @Column(unique = true)
  private String cpf;
  @Column(unique = true)
  private String name;
  @Column(unique = true)
  private String email;
  @Column(unique = true)
  private String phoneNumber;
}
