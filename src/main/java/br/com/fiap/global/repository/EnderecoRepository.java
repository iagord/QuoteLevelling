package br.com.fiap.global.repository;

import br.com.fiap.global.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository <Endereco, Long> {

}
