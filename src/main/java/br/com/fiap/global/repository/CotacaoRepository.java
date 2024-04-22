package br.com.fiap.global.repository;

import br.com.fiap.global.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository <Cotacao, Long>{
    
}
