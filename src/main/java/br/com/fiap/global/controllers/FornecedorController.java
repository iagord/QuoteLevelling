package br.com.fiap.global.controllers;

import br.com.fiap.global.model.Empresas.Fornecedor;
import br.com.fiap.global.repository.FornecedorRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/fornecedor")
public class FornecedorController {

    List<Fornecedor> fornecedores = new ArrayList<>();

    @Autowired
    FornecedorRepository repository;

    @GetMapping
    public Page<Fornecedor> ListAll(@PageableDefault(size = 5) Pageable pageRequest) {
        log.info("buscando todas Fornecedores =D!");
        return repository.findAll(pageRequest);
    }

    @GetMapping("{id}")
    public ResponseEntity<Fornecedor> ListById(@PathVariable Long id) {
        log.info("mostrar Fornecedor com id " + id);
        return ResponseEntity.ok(getFornecedorById(id));

    }

    @PostMapping
    public ResponseEntity<Object> Insert(@RequestBody @Valid Fornecedor Fornecedor) {
        log.info("cadastrando Fornecedor - " + Fornecedor);
        repository.save(Fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Fornecedor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody Fornecedor Fornecedor){
        log.info("atualizando dados do Fornecedor com id " + id);
        getFornecedorById(id);
        Fornecedor.setId_empresa(id);
        repository.save(Fornecedor);

        return ResponseEntity.ok(Fornecedor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id){
        log.info("apagando Fornecedor com id " + id);
        repository.delete(getFornecedorById(id));
        return ResponseEntity.noContent().build();
    }

    private Fornecedor getFornecedorById(Long id){
        return repository.findById(id).orElseThrow(() -> {
            return new RuntimeException();
        });
    }

}
