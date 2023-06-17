package dev.cami.pottencial.techtest.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Seller {
  private Long id;
  private String cpf;
  private String name;
  private String email;
  private String phoneNumber;
}
