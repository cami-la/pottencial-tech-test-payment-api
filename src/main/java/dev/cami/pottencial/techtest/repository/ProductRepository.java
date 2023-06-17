package dev.cami.pottencial.techtest.repository;

import dev.cami.pottencial.techtest.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Item, Long> {
}
