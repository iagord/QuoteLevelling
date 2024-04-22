package br.com.fiap.global.controllers;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.global.model.Cotacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.global.repository.CotacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/cotacao")
public class CotacaoController {

            List<Cotacao> cotacaos = new ArrayList<>();

    @Autowired
    CotacaoRepository repository;

    @GetMapping
    public Page<Cotacao> ListAll(@PageableDefault(size = 5) Pageable pageRequest) {
        log.info("buscando todas cotações =D!");
        return repository.findAll(pageRequest);
    }

     @GetMapping("{id}")
    public ResponseEntity<Cotacao> ListById(@PathVariable Long id) {
        log.info("mostrar Cotaçao com id " + id);
        return ResponseEntity.ok(getCotaçaoById(id));

    }

    @PostMapping
    public ResponseEntity<Object> Insert(@RequestBody @Valid Cotacao Cotacao) {
        log.info("cadastrando Cotaçao - " + Cotacao);
        repository.save(Cotacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(Cotacao);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cotacao> update(@PathVariable Long id, @RequestBody Cotacao Cotacao){
        log.info("atualizando dados do Cotaçao com id " + id);
        getCotaçaoById(id);
        Cotacao.setId_cotacao(id);
        repository.save(Cotacao);

        return ResponseEntity.ok(Cotacao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id){
        log.info("apagando Cotaçao com id " + id);
        repository.delete(getCotaçaoById(id));
        return ResponseEntity.noContent().build();
    }

    private Cotacao getCotaçaoById(Long id){
        return repository.findById(id).orElseThrow(() -> { 
             return new RuntimeException();
         });
        }

}



