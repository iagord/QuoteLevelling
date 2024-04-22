package br.com.fiap.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.global.model.Empresas.Fornecedor;


public interface FornecedorRepository extends JpaRepository <Fornecedor, Long>{
    
}
