package br.com.fiap.global.repository;

import br.com.fiap.global.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MaterialRepository extends JpaRepository <Material, Long>{
    
}
