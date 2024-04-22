package br.com.fiap.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.global.model.Empresas.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    
}
