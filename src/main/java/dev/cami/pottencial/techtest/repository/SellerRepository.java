package dev.cami.pottencial.techtest.repository;

import dev.cami.pottencial.techtest.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
