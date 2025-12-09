package com.example.IntegradoraEstructura.repository;

import com.example.IntegradoraEstructura.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
